using System;
using System.Collections.Generic;
using System.Text;
using System.Drawing;
using System.Windows.Forms;
using System.Drawing.Imaging;


namespace UILib
{
    public class ImageButton 
    {
        internal Rectangle clientArea;
        internal Form owner;
        internal Bitmap image;
        internal Bitmap imageDown;
        internal bool pushed = false;
        internal Point location;
        internal Point start;
        
        internal bool pushedOneTime = false;

        public bool IsPressedOneTime
        {
            get { return pushedOneTime; }
            set { pushedOneTime = value; }
        }
     
    

        public ImageButton(Form owner,Point location)
        {
            this.owner = owner;
            Attach(owner);
            this.location = location;
            this.clientArea = new Rectangle(location.X, location.Y, 30, 30);
        }

      

        public Bitmap Image
        {
            get { return image;}
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
            get { return imageDown;}
            set { imageDown = value; }
        }

      
        public bool HitTest(int x, int y)
        {
            return clientArea.Contains(x, y);
        }

        private void Attach(Form owner)
        {           
            owner.MouseDown += new MouseEventHandler(owner_MouseDown);         
            owner.MouseUp += new MouseEventHandler(owner_MouseUp);
        }

        public virtual void owner_MouseUp(object sender, MouseEventArgs e)
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

//            SendMessage(ButtonCode, "Button Pressed");
        }

        public virtual void owner_MouseDown(object sender, MouseEventArgs e)
        {
            if (this.HitTest(e.X, e.Y))
            {
                using (Graphics gx = owner.CreateGraphics())
                {
                    this.pushed = true;
                    IsPressedOneTime = true;
                    this.Paint(gx);
                }
                start = new Point(e.X, e.Y);            
            }
        }
    
        public void Paint(Graphics gx)
        {
            ImageAttributes attrib = new ImageAttributes();
            Color color = GetTransparentColor(image);
            attrib.SetColorKey(color, color);

            if (!pushed || imageDown == null)
                gx.DrawImage(image, clientArea, 0, 0, clientArea.Width, clientArea.Height, GraphicsUnit.Pixel, attrib);                
            else
                gx.DrawImage(imageDown, clientArea, 0, 0, clientArea.Width, clientArea.Height, GraphicsUnit.Pixel, attrib);                
            
        }

        internal Color GetTransparentColor(Bitmap image)
        {
            return image.GetPixel(0, 0);
        }
    
    }
}
