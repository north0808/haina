/*
 ============================================================================
 Name		 : dbgenerator.c
 Author	     : shaochuan.yang
 Copyright   : haina
 Description : Database file dbgenerator
 ============================================================================
 */

/*******************************************************************************
 * FileName : dbgenerator.c
 * Version  :
 * Purpose  :
 * Authors  : shaochuan.yang
 * Date     : 2009-6-11
 * Notes    :
 * ----------------------------
 * HISTORY OF CHANGES
 * ----------------------------
 *
 ******************************************************************************/



#define DBDBGENERATOR_C



/*---------------------------- include head file -----------------------------*/

#include <io.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include ".\sqlite3_lib\sqlite3.h"

/*---------------------- external variable declaration -----------------------*/



/*----------------- external function prototype declaration ------------------*/



/*----------------------- file-local macro definition ------------------------*/

#define     DB_CREATOR_NAME         " DB Generator "
#define     DB_CREATOR_VERSION      " version 0.1.0 "

#define     MAX_LINE_LENGTH         1024
#define     MAX_PATH_LENGTH         261

/*----------------- file-local constant and type definition ------------------*/



/*--------------------- file-local variables definition ----------------------*/

static char *Argv0;

static char db_file[MAX_PATH_LENGTH];


/*---------------- file-local function prototype declaration -----------------*/



/*--------------------------- function definition ----------------------------*/

static void usage( int showFormat )
{
    fprintf( stderr, "%s%s\n", DB_CREATOR_NAME, DB_CREATOR_VERSION );

    if ( showFormat )
    {
        fprintf( stderr, "SQL_FILENAME format likes *.sql, DB_FILENAME format likes *.db \n" );
    }
    else
    {
        fprintf( stderr, "Usage: %s SQL_FILENAME [DB_FILENAME] \n"
                "  A new database is created if the file does not previously exist.\n", Argv0 );
    }

    exit(1);
}

static void report( void )
{
    char chars[25] = { '\0' };

    fprintf( stderr, "Warning: %s exist, merge new data into the database? (y / n)  ", db_file );
    fgets(chars, 25, stdin );

    if ( 'n' == chars[0] ) /* if user choose no, exit app */
        exit(1);
}

static void print_progress( void )
{
    static int i = 0;

    switch (i)
    {
        case 0:
            fprintf(stderr, "\b"); fprintf(stderr, "\\");
            break;
        case 1:
            fprintf(stderr, "\b"); fprintf(stderr, "|");
            break;
        case 2:
            fprintf(stderr, "\b"); fprintf(stderr, "/");
            break;
        case 3:
            fprintf(stderr, "\b"); fprintf(stderr, "-");
            break;
        default:
            break;
    }

    if (i++ >= 3) i = 0;
}

static int create_db( char* sql_file,  char* db_file )
{
	int res = 0;
    char               		*szLine = NULL;
    char               		*szSQL = NULL;
    FILE               		*fp = NULL;
    int                     bComplete = 0;

    /* Open or create database file. */
    sqlite3* sql_con_ptr = NULL;
    char pragma[64];
    char *error = NULL;
    int rc;

    if ( NULL == sql_file || NULL == db_file )
    {
        fprintf(stderr, "Invalid sql file.\n");
        return -1;
    }

    rc = sqlite3_open((const char*)db_file, &sql_con_ptr);
    if(SQLITE_OK != rc)
    {
        fprintf(stderr, "sqlite3_open(): - Sqlite3 error: (%d).\n", rc);
        return -1;
    }

    sqlite3_busy_timeout( sql_con_ptr, 60000 );

    // set the database page size.
    sprintf( pragma, "PRAGMA page_size = %d;", 1024 );
    rc = sqlite3_exec( sql_con_ptr, pragma, NULL, NULL, &error );
    if (rc != SQLITE_OK && NULL != error)
    {
        fprintf(stderr, "sqlite3_exec():- Set page_size(1024) error: %s (%d).\n", error, rc);
        sqlite3_free(error);
        error = NULL;
    }

    // set the database encoding type.
    sprintf( pragma, "PRAGMA encoding = '%s';", "UTF-8" );
    rc = sqlite3_exec( sql_con_ptr, pragma, NULL, NULL, &error );
    if (rc != SQLITE_OK && NULL != error)
    {
        fprintf(stderr, "sqlite3_exec(): set encoding=(UFT-8) error: %s (%d).\n", error, rc);
        sqlite3_free(error);
        error = NULL;
    }

    /* Malloc memory to load sql script */
    szLine = (char *)malloc(sizeof(char) * MAX_LINE_LENGTH);
    szSQL = (char *)malloc(sizeof(char) * MAX_LINE_LENGTH * 4);
    if (!szLine || !szSQL)
        goto error_0;

    /* Open sql script file */
    if ( (fp = fopen( sql_file, "r" )) == NULL )
    {
        fprintf(stderr, "\nOpen sql script file error.\n " );

        res = -1;
        goto error_0;
    }

    *szSQL = 0;

    while ( fgets(szLine, MAX_LINE_LENGTH, fp) )
    {
        char *p;

        if (*szLine == 0)
            continue;

        /* Read a line */
        for (p = szLine; *p != '#' && *p != '\0' && (*p == ' ' || *p == '\t' || *p == '\n' || *p == '\r'); p++);

        /* Skip comment and blank line */
        if ( *p == '\0' || *p =='#' )
            continue;

        /* Read " GO ", load an integrity sql command ok */
        if ( (*p == 'G' || *p == 'g') && (*(p+1) == 'O' || *(p+1) == 'o') )
        {
            for(p+=2; *p != '\0' && (*p == ' ' || *p == '\t' || *p == '\n' || *p == '\r'); p++);

            if(*p == '\0')
                bComplete = 1;
        }

        if ( !bComplete )
        {
            strcat(szSQL, szLine);  /* cache the sql line */
        }
        else
        {
            rc = sqlite3_exec( (sqlite3*)sql_con_ptr, (const char*)szSQL, NULL, NULL, &error );
            if (rc != SQLITE_OK && NULL != error)
            {
                fprintf(stderr, "sqlite3_exec(): Call sqlite3_exec SQL error: %d -%s.\n", rc, error);
                sqlite3_free( error );
                error = NULL;
            }
            else
            {
                /* print progress */
                print_progress();
            }

            *szSQL = 0;
            bComplete = 0;
        }
    }

    fprintf(stderr, "\b ");
	fprintf(stderr, "\nOk!\n");
    free(szLine);
    free(szSQL);
    fclose(fp);

    fprintf(stderr, "Closing database...\n");
    if ( (rc = sqlite3_close( (sqlite3*)sql_con_ptr )) != SQLITE_OK )
    {
        fprintf(stderr, "sqlite3_close(): - sqlite3_close error: %d - %s.\n", rc, sqlite3_errmsg( (sqlite3*)sql_con_ptr ));
        return rc;
    }
	fprintf(stderr, "Ok!\n");

    return 0;

error_0:
    if (szLine)
        free(szLine);
    if (szSQL)
        free(szSQL);
    if (fp)
        fclose(fp);
    if (sql_con_ptr)
    if ( (rc = sqlite3_close( (sqlite3*)sql_con_ptr )) != SQLITE_OK )
    {
        fprintf(stderr, "sqlite3_close(): - sqlite3_close error: %d - %s.\n", rc, sqlite3_errmsg( (sqlite3*)sql_con_ptr ));
        return rc;
    }

    return res;
}

int main( int argc, char** argv )
{
    char * suffix = NULL;

    Argv0 = argv[0];
    if ( 1 == argc )
    {
        usage(0);
    }

    /* check the sql script file name format */
    suffix = strrchr(argv[1], '.');
    if ( 0 != strcmp(suffix, ".sql") )
    {
        usage(1);
    }

    /* check the sql db file name format */
    if ( argc > 2 )
    {
        strcpy( db_file, argv[2] );
        suffix = strrchr(db_file, '.');
        if ( 0 != strcmp(suffix, ".db") )
        {
            usage(1);
        }
    }
    else  /* set db file name same with sql script file name except for suffix */
    {
        strcpy( db_file, argv[1] );
        suffix = strrchr(db_file, '.');
        suffix[0] = '\0';
        strcat( suffix, ".db" );
    }

    /* check whether the db file exist */
    if( _access(db_file, 0) == 0 )
    {
        report(); /* file exist */
    }

    fprintf( stderr, "\nCreating database [%s]....", db_file);
    create_db( argv[1], db_file );

    return 0;
}

#undef DBDBGENERATOR_C
