using System;
using System.Collections.Generic;
using System.Text;
using System.Windows.Forms;
using System.Collections;
using System.Drawing.Drawing2D;
using System.Drawing;
using System.Reflection;

namespace UILib
{
    public delegate void SelecteChage(object sender,EventArgs e);

    public class ImageTabControl:Control
    {
        //ImageList imgs = new ImageList();

        public ArrayList Imagtabs = new ArrayList();

        DrawPanel _PContext = new DrawPanel();

        /// <summary>
        /// °´Å¥Panel
        /// </summary>
        DrawPanel _TopContext = new DrawPanel();

        PictureBox _topPb = new PictureBox();
        PictureBox _pContextPb = new PictureBox();

        Bitmap bitmapBackImageDefault;
        string path = "";
        //Graphics gxBuffer;
        //Bitmap offBitmap;

        public void ImageTabControlload() 
        {
           
        }

        public ImageTabControl() 
        {
            
            //int MaxLen = (this.Width > this.Height) ? this.Width : this.Height;
            //offBitmap = new Bitmap(MaxLen, MaxLen);
            //gxBuffer = Graphics.FromImage(offBitmap);
            //gxBuffer.Clear(this.BackColor);
            //gxBuffer.DrawImage(bitmapBackImageDefault, 0, 0);
            //_PContext.Visible = false;
            //_TopContext.Visible = false;
        }

        System.Drawing.Point BtnLastLocation = new System.Drawing.Point(1,1);

        public void AddTabPage (ImgTabPage imgTabPage)
        {
            Imagtabs.Add(imgTabPage);

            imgTabPage.TabPageSelected += new SelecteChage(imgTabPage_TabPageSelected);
            imgTabPage.Id = Imagtabs.Count-1;
            _TopContext.Controls.Add(imgTabPage.PicTureBox);

            imgTabPage.PicTureBox.Location = BtnLastLocation;

            if (Imagtabs.Count == 1)
            {
              imgTabPage.ConTextPane.Visible=true;
            }

            _PContext.Controls.Add(imgTabPage.ConTextPane);


            BtnLastLocation = imgTabPage.PicTureBox.Location;
            BtnLastLocation.X = BtnLastLocation.X + imgTabPage.PicTureBox.Width + 1;


            imgTabPage.ConTextPane.Dock = DockStyle.Fill;

           
        }

        

        void imgTabPage_TabPageSelected(object sender, EventArgs e)
        {
            int sId=(sender as ImgTabPage).Id;
            if (sId != selectIndex)
            {
                //(Imagtabs[sId] as ImgTabPage).ConTextPane.Visible = true;
                (Imagtabs[selectIndex] as ImgTabPage).sSate = false;
                selectIndex = sId;
            }
        }

      

        public int selectIndex = 0;

        public DockStyle DockBottonPanelStyle = DockStyle.Top;


        int btHeight = 30;
        public int BtHigh
        {
            get 
            {
                return btHeight;
            }
            set 
            {
                btHeight = value;
            }
        }

        public override void Refresh() 
        {
            _TopContext.Height = btHeight;
            _PContext.Height = this.Height - _TopContext.Height;

            if (DockBottonPanelStyle == DockStyle.Top)
            {
                _PContext.Dock = DockStyle.Bottom;
                _TopContext.Dock = DockStyle.Top;
            }
            else
            {
                _PContext.Dock = DockStyle.Top;
                _TopContext.Dock = DockStyle.Bottom;
            }
            this.Controls.Add(_PContext);
            this.Controls.Add(_TopContext);
            path = System.IO.Path.GetDirectoryName(Assembly.GetExecutingAssembly().GetName().CodeBase);
            bitmapBackImageDefault = new Bitmap(path + @"\wallpaperDefault.bmp");
            _topPb.Image = bitmapBackImageDefault;
            _pContextPb.Image = bitmapBackImageDefault;
            _topPb.Dock = DockStyle.Fill;
            _pContextPb.Dock = DockStyle.Fill;
            _PContext.Controls.Add(_pContextPb);
            _TopContext.Controls.Add(_topPb); ;
          
        }
       
        
    }


   
}
