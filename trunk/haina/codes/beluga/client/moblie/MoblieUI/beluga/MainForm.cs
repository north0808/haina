using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace beluga
{
  
    public partial class MainForm : Form
    {
        public event TxTSearchChageHandler FireSearchChage;

        /// <summary>
        /// 当前状态
        /// </summary>
        public ContactTag CurrentTag=ContactTag.Contact_Page_Mobile;

        public MainForm()
        {
            InitializeComponent();
            this.Update();
        }

        protected override void OnPaint(PaintEventArgs e)
        {
               
         
        }

        private void txt_Search_LostFocus(object sender, EventArgs e)
        {

        }

        private void txt_Search_TextChanged(object sender, EventArgs e)
        {
            if (FireSearchChage != null)
            {
                FireSearchChage(sender, e, CurrentTag);
            }
        }

        private void PtrBoxSearch_Click(object sender, EventArgs e)
        {
            if (FireSearchChage != null)
            {
                FireSearchChage(sender, e, CurrentTag);
            }
        }
    }
}