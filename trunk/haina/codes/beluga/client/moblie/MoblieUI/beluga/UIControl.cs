using System;
using System.Collections.Generic;
using System.Text;

namespace beluga
{
    public enum ContactTag:int
    {
        /// <summary>
        /// 手机联系人分页
        /// </summary>
        Contact_Page_Mobile=1,
        /// <summary>
        /// QQ分页
        /// </summary>
        Contact_Page_IM_QQ = 2,//暂存
        /// <summary>
        /// MSN分页
        /// </summary>
        Contact_Page_IM_MSN=3,//提交(完成)
        /// <summary>
        /// 其他聊天工具分页，如GTalk
        /// </summary>
        Contact_Page_IM_Other=4,
        /// <summary>
        /// 最近来电联系人分组显示
        /// </summary>
        Contact_Recent_Call=5,
        /// <summary>
        /// 最近短信联系人分组显示
        /// </summary>
        Contact_Recent_Sms=6,
        /// <summary>
        /// 最近QQ聊天好友分组显示
        /// </summary>
        Contact_Recent_QQ=7,	
        /// <summary>
        /// 最近MSN聊天好友分组显示
        /// </summary>
        Contact_Recent_MSN=8	

     
    }


    public delegate void TxTSearchChageHandler(object sender,EventArgs e,ContactTag tag); 
}
