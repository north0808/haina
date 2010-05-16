﻿namespace Resco.Controls.AdvancedTree
{
    using System;
    using System.Drawing;
    using System.Drawing.Imaging;
    using System.Windows.Forms;

    public class ImageCell : Cell
    {
        private Resco.Controls.AdvancedTree.Alignment m_Alignment;
        private bool m_bAutoResize;
        private bool m_bAutoTransparent;
        private ImageAttributes m_ia;
        private System.Windows.Forms.ImageList m_ImageList;
        private Color m_transparentColor;

        public ImageCell()
        {
            this.m_ia = new ImageAttributes();
            this.m_ImageList = null;
            this.m_transparentColor = Resco.Controls.AdvancedTree.AdvancedTree.TransparentColor;
            this.m_bAutoTransparent = false;
            this.m_bAutoResize = false;
            this.m_Alignment = Resco.Controls.AdvancedTree.Alignment.TopLeft;
        }

        public ImageCell(ImageCell cell) : base(cell)
        {
            this.m_ia = new ImageAttributes();
            this.m_ImageList = cell.m_ImageList;
            this.m_bAutoTransparent = cell.m_bAutoTransparent;
            this.m_bAutoResize = cell.m_bAutoResize;
            this.m_Alignment = cell.m_Alignment;
            this.TransparentColor = cell.TransparentColor;
        }

        public override Cell Clone()
        {
            return new ImageCell(this);
        }

        protected override void DrawContent(Graphics gr, Rectangle drawbounds, object data)
        {
            Image original = this.GetImage(data);
            if (original != null)
            {
                if (this.m_bAutoTransparent)
                {
                    Bitmap bitmap = new Bitmap(original);
                    Color pixel = bitmap.GetPixel(0, 0);
                    this.m_ia.SetColorKey(pixel, pixel);
                    bitmap.Dispose();
                }
                int x = 0;
                int y = 0;
                int width = drawbounds.Width;
                int num4 = original.Width;
                int height = drawbounds.Height;
                int num6 = original.Height;
                Rectangle a = new Rectangle(0, 0, num4, num6);
                if (!this.m_bAutoResize)
                {
                    switch (this.m_Alignment)
                    {
                        case Resco.Controls.AdvancedTree.Alignment.MiddleCenter:
                            x = (width / 2) - (num4 / 2);
                            y = (height / 2) - (num6 / 2);
                            break;

                        case Resco.Controls.AdvancedTree.Alignment.MiddleRight:
                            x = width - num4;
                            y = (height / 2) - (num6 / 2);
                            break;

                        case Resco.Controls.AdvancedTree.Alignment.MiddleLeft:
                            y = (height / 2) - (num6 / 2);
                            break;

                        case Resco.Controls.AdvancedTree.Alignment.BottomCenter:
                            x = (width / 2) - (num4 / 2);
                            y = height - num6;
                            break;

                        case Resco.Controls.AdvancedTree.Alignment.BottomRight:
                            x = width - num4;
                            y = height - num6;
                            break;

                        case Resco.Controls.AdvancedTree.Alignment.BottomLeft:
                            y = height - num6;
                            break;

                        case Resco.Controls.AdvancedTree.Alignment.TopCenter:
                            x = (width / 2) - (num4 / 2);
                            break;

                        case Resco.Controls.AdvancedTree.Alignment.TopRight:
                            x = width - num4;
                            break;
                    }
                    Rectangle b = new Rectangle(0, 0, width, height);
                    b.Offset(-x, -y);
                    a = Rectangle.Intersect(a, b);
                    drawbounds.Width = a.Width;
                    drawbounds.Height = a.Height;
                    drawbounds.Offset(x, y);
                }
                gr.DrawImage(original, drawbounds, a.X, a.Y, a.Width, a.Height, GraphicsUnit.Pixel, this.m_ia);
            }
        }

        protected virtual Image GetImage(object data)
        {
            if (this.m_ImageList != null)
            {
                int num = -1;
                try
                {
                    num = Convert.ToInt32(data);
                }
                catch (Exception)
                {
                }
                if ((num >= 0) && (num < this.m_ImageList.Images.Count))
                {
                    return ImageCache.GlobalCache[this.m_ImageList, num];
                }
            }
            return null;
        }

        private void OnDetachImageList(object sender, EventArgs e)
        {
            this.ImageList = null;
        }

        protected virtual bool ShouldSerializeAlignment()
        {
            return (this.m_Alignment != Resco.Controls.AdvancedTree.Alignment.TopLeft);
        }

        protected virtual bool ShouldSerializeImageList()
        {
            return (this.m_ImageList != null);
        }

        public Resco.Controls.AdvancedTree.Alignment Alignment
        {
            get
            {
                return this.m_Alignment;
            }
            set
            {
                if (this.m_Alignment != value)
                {
                    this.m_Alignment = value;
                    this.OnChanged(this, TreeRepaintEventArgs.Empty);
                }
            }
        }

        public bool AutoResize
        {
            get
            {
                return this.m_bAutoResize;
            }
            set
            {
                if (this.m_bAutoResize != value)
                {
                    this.m_bAutoResize = value;
                    this.OnChanged(this, TreeRepaintEventArgs.Empty);
                }
            }
        }

        public bool AutoTransparent
        {
            get
            {
                return this.m_bAutoTransparent;
            }
            set
            {
                if (this.m_bAutoTransparent != value)
                {
                    this.m_bAutoTransparent = value;
                    this.OnChanged(this, TreeRepaintEventArgs.Empty);
                }
            }
        }

        public int ImageIndex
        {
            get
            {
                int num = -1;
                if (base.CellSource.SourceType == CellSourceType.Constant)
                {
                    try
                    {
                        num = Convert.ToInt32(base.CellSource.ConstantData);
                    }
                    catch (Exception)
                    {
                    }
                }
                return num;
            }
            set
            {
                if (value >= 0)
                {
                    base.CellSource.ConstantData = Convert.ToString(value);
                }
            }
        }

        public System.Windows.Forms.ImageList ImageList
        {
            get
            {
                return this.m_ImageList;
            }
            set
            {
                if (this.m_ImageList != null)
                {
                    ImageCache.GlobalCache.Clear(this.m_ImageList);
                }
                if (value != this.m_ImageList)
                {
                    EventHandler handler = new EventHandler(this.OnDetachImageList);
                    if (((this.m_ImageList != null) && (this.m_ImageList.Site != null)) && this.m_ImageList.Site.DesignMode)
                    {
                        this.m_ImageList.Disposed -= handler;
                    }
                    this.m_ImageList = value;
                    if (((this.m_ImageList != null) && (this.m_ImageList.Site != null)) && this.m_ImageList.Site.DesignMode)
                    {
                        value.Disposed += handler;
                    }
                }
                this.OnChanged(this, TreeRepaintEventArgs.Empty);
            }
        }

        public Color TransparentColor
        {
            get
            {
                return this.m_transparentColor;
            }
            set
            {
                if (this.m_transparentColor != value)
                {
                    if (value.IsEmpty)
                    {
                        value = Color.Transparent;
                    }
                    this.m_transparentColor = value;
                    this.m_ia.ClearColorKey();
                    if (!(this.m_transparentColor == Resco.Controls.AdvancedTree.AdvancedTree.TransparentColor))
                    {
                        this.m_ia.SetColorKey(this.m_transparentColor, this.m_transparentColor);
                    }
                    this.OnChanged(this, TreeRepaintEventArgs.Empty);
                }
            }
        }
    }
}

