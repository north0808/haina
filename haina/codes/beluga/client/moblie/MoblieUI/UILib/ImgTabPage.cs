using System;
using System.Collections.Generic;
using System.Text;
using System.Windows.Forms;
using System.Drawing;

namespace UILib
{
    public class ImgTabPage
    {

        public event SelecteChage TabPageSelected;

        public ImgTabPage() 
        {
            //_selectimage.Height = 20;
            _picTureBox.Click += new EventHandler(_selectimage_Click);
            _panel.Visible = false;
        }

        void _selectimage_Click(object sender, EventArgs e)
        {
            if (_sImg != null)
            {
                _picTureBox.Image = _sImg;
            }
                
            if (TabPageSelected != null)
            {
                TabPageSelected(this,e);
            }
        }

        PictureBox _picTureBox = new PictureBox();

        public PictureBox PicTureBox
        {
            get
            {
                return _picTureBox;
            }
        }

        Image _unsImg;
        public Image UnSImg 
        {
            set
            {
                _picTureBox.Size = value.Size;
                _unsImg = value;
                _picTureBox.Image = value;
            }
            get 
            {
                return _unsImg;
            }
        }
        Image _sImg;
        public Image SImg 
        {
            get
            {
                return _sImg;
            }
            set 
            {
                _sImg = value;
            }
        }

        DrawPanel _panel = new DrawPanel();
        public DrawPanel ConTextPane 
        {
            get 
            {
                return _panel;
             
            }
            set 
            {
                _panel = value;
            }
        }

        public int Id = 0;

        public bool sSate
        {
            set 
            {
                if (!value)
                {
                    _picTureBox.Image = _unsImg;
                    _panel.Visible = false;
                }
            }
        }

    }
}
