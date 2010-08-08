﻿using System;

using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace BelugaMobile.BaseControl
{
    public delegate void sendTextHandler(string text);
    public partial class EditTextForm : Form
    {

        static EditTextForm objInstance = null;
        public static EditTextForm getInstance()
        {
            if (objInstance == null) objInstance = new EditTextForm();
            return objInstance;
        }

        public EditTextForm()
        {
            InitializeComponent();
        }
        public event sendTextHandler sendText;
        private void Edit_textBox_TextChanged(object sender, EventArgs e)
        {
            if (sendText != null)
            {
                sendText(this.Edit_textBox.Text);
            }
        }

        private void pictureBox2_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        public string EditText 
        {
            get 
            {
                return this.Edit_textBox.Text;
            }
            set
            {
                this.Edit_textBox.Text = value;
            }
        }

        private void pictureBox1_Click(object sender, EventArgs e)
        {
            this.Edit_textBox.Text ="";
        }
    }
}