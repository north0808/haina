namespace beluga
{
    partial class MainForm
    {
        /// <summary>
        /// 必需的设计器变量。
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// 清理所有正在使用的资源。
        /// </summary>
        /// <param name="disposing">如果应释放托管资源，为 true；否则为 false。</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows 窗体设计器生成的代码

        /// <summary>
        /// 设计器支持所需的方法 - 不要
        /// 使用代码编辑器修改此方法的内容。
        /// </summary>
        private void InitializeComponent()
        {
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(MainForm));
            this.txt_Search = new System.Windows.Forms.TextBox();
            this.picBoxSearch = new System.Windows.Forms.PictureBox();
            this.MessageTabControl = new UILib.ImageTabControl();
            this.PtrBoxSearch = new System.Windows.Forms.PictureBox();
            this.SuspendLayout();
            // 
            // txt_Search
            // 
            this.txt_Search.Location = new System.Drawing.Point(23, 0);
            this.txt_Search.Name = "txt_Search";
            this.txt_Search.Size = new System.Drawing.Size(194, 21);
            this.txt_Search.TabIndex = 0;
            this.txt_Search.LostFocus += new System.EventHandler(this.txt_Search_LostFocus);
            this.txt_Search.TextChanged += new System.EventHandler(this.txt_Search_TextChanged);
            // 
            // picBoxSearch
            // 
            this.picBoxSearch.Image = ((System.Drawing.Image)(resources.GetObject("picBoxSearch.Image")));
            this.picBoxSearch.Location = new System.Drawing.Point(3, 0);
            this.picBoxSearch.Name = "picBoxSearch";
            this.picBoxSearch.Size = new System.Drawing.Size(20, 21);
            this.picBoxSearch.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            // 
            // MessageTabControl
            // 
            this.MessageTabControl.BackColor = System.Drawing.SystemColors.Highlight;
            this.MessageTabControl.BtHigh = 30;
            this.MessageTabControl.Location = new System.Drawing.Point(3, 22);
            this.MessageTabControl.Name = "MessageTabControl";
            this.MessageTabControl.Size = new System.Drawing.Size(234, 298);
            this.MessageTabControl.TabIndex = 2;
            this.MessageTabControl.Text = "imageTabControl1";
            // 
            // PtrBoxSearch
            // 
            this.PtrBoxSearch.Image = ((System.Drawing.Image)(resources.GetObject("PtrBoxSearch.Image")));
            this.PtrBoxSearch.Location = new System.Drawing.Point(217, 0);
            this.PtrBoxSearch.Name = "PtrBoxSearch";
            this.PtrBoxSearch.Size = new System.Drawing.Size(20, 21);
            this.PtrBoxSearch.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.PtrBoxSearch.Click += new System.EventHandler(this.PtrBoxSearch_Click);
            // 
            // MainForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(96F, 96F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Dpi;
            this.AutoScroll = true;
            this.ClientSize = new System.Drawing.Size(240, 320);
            this.Controls.Add(this.PtrBoxSearch);
            this.Controls.Add(this.MessageTabControl);
            this.Controls.Add(this.picBoxSearch);
            this.Controls.Add(this.txt_Search);
            this.Location = new System.Drawing.Point(0, 0);
            this.Name = "MainForm";
            this.WindowState = System.Windows.Forms.FormWindowState.Maximized;
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.TextBox txt_Search;
        private System.Windows.Forms.PictureBox picBoxSearch;
        private UILib.ImageTabControl MessageTabControl;
        private System.Windows.Forms.PictureBox PtrBoxSearch;



    }
}

