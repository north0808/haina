using System;
using System.Collections.Generic;
using System.Text;
using System.Drawing;
using System.Windows.Forms;
using System.Drawing.Imaging;

namespace UILib
{
    public class SlideButton
    {
        private Rectangle clientArea;
        private Form owner;
        private Bitmap image;
        private Bitmap imageDown;
        public bool pushed = false;
        private Point location;
        private Point start;
        int mousePos = 0;
        private int leftLimit = 16;
        private int rightLimit = 220;
        private System.Windows.Forms.Timer timeAnimation = new Timer();
        public bool unLock = false;

        public SlideButton(Form owner, Point location)
        {
            this.location = location;
            this.clientArea = new Rectangle(location.X, location.Y, 30, 30);
            this.owner = owner;
            this.Attach(owner);
            timeAnimation.Interval = 1;
            timeAnimation.Enabled = false;
            //this.timeAnimation.Tick += new System.EventHandler(this.timeAnimation_Tick);
        }

        private void timeAnimation_Tick(object sender, EventArgs e)
        {
            int x = (start.X - 20);
            Move(x);
        }

        public Bitmap Image
        {
            get { return image; }
            set
            {
                image = value;
                if (image != null)
                {
                    clientArea = new Rectangle(location.X, location.Y, image.Width, image.Height);
                }
            }
        }

        public Bitmap ImageDown
        {
            get { return imageDown; }
            set { imageDown = value; }
        }

        private void Attach(Form owner)
        {
            owner.MouseDown += new MouseEventHandler(owner_MouseDown);
            owner.MouseMove += new MouseEventHandler(owner_MouseMove);
            owner.MouseUp += new MouseEventHandler(owner_MouseUp);
        }

        void owner_MouseUp(object sender, MouseEventArgs e)
        {
            if (pushed)
            {
                using (Graphics gx = owner.CreateGraphics())
                {
                    this.pushed = false;
                    this.Paint(gx);
                }
                pushed = false;

            }
            timeAnimation.Enabled = true;
        }

        void Move(int x)
        {
            int shift = x - start.X;
            int newX = (this.clientArea.X + shift);// -mousePos;
            if (newX <= leftLimit)
            {
                newX = leftLimit;
                timeAnimation.Enabled = false;
            }
            if (newX + this.clientArea.Width >= rightLimit)
            {
                newX = rightLimit - this.clientArea.Width;
                unLock = true;
            }

            this.clientArea = new Rectangle(newX, clientArea.Y, this.clientArea.Width, this.clientArea.Height);
            //owner.Invalidate(new Rectangle(0, 258, 240, 70));
            start.X = x;
        }

        void owner_MouseMove(object sender, MouseEventArgs e)
        {
            Move(e.X);

        }

        void owner_MouseDown(object sender, MouseEventArgs e)
        {
            if (this.HitTest(e.X, e.Y))
            {
                using (Graphics gx = owner.CreateGraphics())
                {
                    this.pushed = true;
                    this.Paint(gx);
                }
                start = new Point(e.X, e.Y);
                mousePos = start.X - clientArea.X;
            }
        }

        public bool HitTest(int x, int y)
        {
            return clientArea.Contains(x, y);
        }

        public void Paint(Graphics gx)
        {
            
            if (timeAnimation.Enabled)
            {
                int x = (start.X - 20);
                Move(x);
            }

            ImageAttributes attrib = new ImageAttributes();
            Color color = GetTransparentColor(image);
            attrib.SetColorKey(color, color);

            if (!pushed || imageDown == null)
                gx.DrawImage(image, clientArea, 0, 0, clientArea.Width, clientArea.Height, GraphicsUnit.Pixel, attrib);
            else
                gx.DrawImage(imageDown, clientArea, 0, 0, clientArea.Width, clientArea.Height, GraphicsUnit.Pixel, attrib);

        }

        private Color GetTransparentColor(Bitmap image)
        {
            return image.GetPixel(0, 0);
        }

        internal void ResetPosition()
        {
            timeAnimation.Enabled = true;
            Move(start.X - 20);
        }
    }
}

  