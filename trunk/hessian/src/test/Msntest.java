package test;


   
 import java.text.SimpleDateFormat;  
 import java.util.Date;  
 import net.sf.jml.MsnContact;  
 import net.sf.jml.MsnList;  
 import net.sf.jml.MsnMessenger;  
 import net.sf.jml.MsnProtocol;  
 import net.sf.jml.MsnSwitchboard;  
 import net.sf.jml.MsnUserStatus;  
 import net.sf.jml.event.MsnAdapter;  
 import net.sf.jml.impl.MsnContactImpl;  
 import net.sf.jml.impl.MsnMessengerFactory;  
 import net.sf.jml.message.MsnControlMessage;  
 import net.sf.jml.message.MsnDatacastMessage;  
 import net.sf.jml.message.MsnInstantMessage;  
 import net.sf.jml.message.MsnSystemMessage;  
 import net.sf.jml.message.MsnUnknownMessage;  
   
 /** 
  * 
  * @author kenter 
  */  
 public class Msntest extends MsnAdapter {  
   
     private MsnMessenger messenger = null;  
     private String email = null,  password = null;  
   
     /** */  
     /** Creates a new instance of Msntest */  
     public Msntest() {  
     }  
   
     public static void main(String args[])  
             throws ClassNotFoundException, InstantiationException, IllegalAccessException {  
         Msntest msn = (Msntest) Class.forName("test.Msntest").newInstance();//创建类实例  
         msn.setEmail("fuxiang2009@live.cn");//设置登录用户  
         msn.setPassword("6687349a");//设置密码  
         msn.start();  
     }  
   
     //打印信息  
     private static void msg(Object obj) {  
         SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");  
         if (obj instanceof Throwable) {  
             System.err.println("[" + sdf.format(new Date()) + "] " + obj);  
         } else {  
             System.out.println("[" + sdf.format(new Date()) + "] " + obj);  
         }  
     }  
   
     private void start() {  
         messenger = MsnMessengerFactory.createMsnMessenger(email, password);//创建MsnMessenger  
         messenger.setSupportedProtocol(new MsnProtocol[]{MsnProtocol.MSNP12});//设置登录协议  
         messenger.getOwner().setInitStatus(MsnUserStatus.ONLINE);//设置用户状态  
         messenger.addListener(this);//注册事件  
         messenger.login();//登录  
     }  
   
     //收到正常信息的时候发生  
     @Override  
     public void instantMessageReceived(MsnSwitchboard switchboard,  
             MsnInstantMessage message, MsnContact contact) {  
         msg(contact.getDisplayName() + "对我说：" + message.getContent());  
         //如果聊天内容为exit，退出  
         if (message.getContent().trim().equalsIgnoreCase("exit")) {  
             msg(contact.getDisplayName() + "对我发出退出指令。");  
             messenger.logout();  
             System.exit(0);  
         }  
         message.setContent("Hello,I'm robot!");//设置要发送消息内容  
         message.setFontRGBColor((int) (Math.random() * 255 * 255 * 255));//设置消息的文本颜色  
         switchboard.sendMessage(message);//发送信息  
     }  
   
     //收到系统信息的时候发生，登录时  
     @Override  
     public void systemMessageReceived(MsnMessenger messenger,  
             MsnSystemMessage message) {  
         //msg(messenger + " recv system message " + message);  
     }  
   
     //当在联系人聊天窗口获得光标并按下第一个键时发生  
     @Override  
     public void controlMessageReceived(MsnSwitchboard switchboard,  
             MsnControlMessage message, MsnContact contact) {  
         msg(contact.getFriendlyName() + "正在输入文字。");  
     }  
   
     //异常时发生  
     @Override  
     public void exceptionCaught(MsnMessenger messenger, Throwable throwable) {  
         msg(messenger + throwable.toString());  
         msg(throwable);  
     }  
   
     //登录完成时发生  
     @Override  
     public void loginCompleted(MsnMessenger messenger) {  
         msg(messenger.getOwner().getDisplayName() + "登录成功！");  
     //messenger.getOwner().setDisplayName("天天持之以恒");  
     }  
   
     //注销时发生  
     @Override  
     public void logout(MsnMessenger messenger) {  
         msg(messenger + " logout");  
     }  
   
     //收到系统广播信息时发生  
     @Override  
     public void datacastMessageReceived(MsnSwitchboard switchboard,  
             MsnDatacastMessage message, MsnContact friend) {  
         msg(switchboard + " recv datacast message " + message);  
         switchboard.sendMessage(message, false);  
     }  
   
     //收到目前不能处理的信息时发生  
     @Override  
     public void unknownMessageReceived(MsnSwitchboard switchboard,  
             MsnUnknownMessage message, MsnContact friend) {  
         //msg(switchboard + " recv unknown message " + message);  
     }  
   
     @Override  
     public void contactListInitCompleted(MsnMessenger messenger) {  
         listContacts();  
     }  
   
     /** */  
     /** 
      * 更新好友列表完成时发生 
      */  
     @Override  
     public void contactListSyncCompleted(MsnMessenger messenger) {  
         listContacts();  
     }  
   
     /** */  
     /** 
      * 关闭一个聊天窗口时发生 
      */  
     @Override  
     public void switchboardClosed(MsnSwitchboard switchboard) {  
         msg("switchboardStarted " + switchboard);  
     }  
   
     /** */  
     /** 
      * 打开一个聊天窗口时发生 
      */  
     @Override  
     public void switchboardStarted(MsnSwitchboard switchboard) {  
         msg("switchboardStarted " + switchboard);  
     }  
   
     //打印联系人  
     private void listContacts() {  
         MsnContact[] cons = messenger.getContactList().getContactsInList(MsnList.AL);  
         if (cons.length == 0) {  
             msg("空");  
         } else {  
             msg("你现在有" + cons.length + "个联系人");  
         }  
         for (int i = 0; i < cons.length; i++) {  
             String personal = ((MsnContactImpl) cons[i]).getPersonalMessage().equals("")  
                     ? "空" : ((MsnContactImpl) cons[i]).getPersonalMessage();  
             msg(cons[i].getDisplayName() + " " + cons[i].getEmail() + " " + cons[i].getStatus() + " " + personal);  
         }  
     }  
   
     public String getEmail() {  
         return email;  
     }  
   
     public void setEmail(String email) {  
         this.email = email;  
     }  
   
     public String getPassword() {  
         return password;  
     }  
   
     public void setPassword(String password) {  
         this.password = password;  
     }  
 } 