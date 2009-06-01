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
         Msntest msn = (Msntest) Class.forName("test.Msntest").newInstance();//������ʵ��  
         msn.setEmail("fuxiang2009@live.cn");//���õ�¼�û�  
         msn.setPassword("6687349a");//��������  
         msn.start();  
     }  
   
     //��ӡ��Ϣ  
     private static void msg(Object obj) {  
         SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");  
         if (obj instanceof Throwable) {  
             System.err.println("[" + sdf.format(new Date()) + "] " + obj);  
         } else {  
             System.out.println("[" + sdf.format(new Date()) + "] " + obj);  
         }  
     }  
   
     private void start() {  
         messenger = MsnMessengerFactory.createMsnMessenger(email, password);//����MsnMessenger  
         messenger.setSupportedProtocol(new MsnProtocol[]{MsnProtocol.MSNP12});//���õ�¼Э��  
         messenger.getOwner().setInitStatus(MsnUserStatus.ONLINE);//�����û�״̬  
         messenger.addListener(this);//ע���¼�  
         messenger.login();//��¼  
     }  
   
     //�յ�����Ϣ��ʱ����  
     @Override  
     public void instantMessageReceived(MsnSwitchboard switchboard,  
             MsnInstantMessage message, MsnContact contact) {  
         msg(contact.getDisplayName() + "����˵��" + message.getContent());  
         //�����������Ϊexit���˳�  
         if (message.getContent().trim().equalsIgnoreCase("exit")) {  
             msg(contact.getDisplayName() + "���ҷ����˳�ָ�");  
             messenger.logout();  
             System.exit(0);  
         }  
         message.setContent("Hello,I'm robot!");//����Ҫ������Ϣ����  
         message.setFontRGBColor((int) (Math.random() * 255 * 255 * 255));//������Ϣ���ı���ɫ  
         switchboard.sendMessage(message);//������Ϣ  
     }  
   
     //�յ�ϵͳ��Ϣ��ʱ�����¼ʱ  
     @Override  
     public void systemMessageReceived(MsnMessenger messenger,  
             MsnSystemMessage message) {  
         //msg(messenger + " recv system message " + message);  
     }  
   
     //����jϵ�����촰�ڻ�ù�겢���µ�һ���ʱ����  
     @Override  
     public void controlMessageReceived(MsnSwitchboard switchboard,  
             MsnControlMessage message, MsnContact contact) {  
         msg(contact.getFriendlyName() + "�����������֡�");  
     }  
   
     //�쳣ʱ����  
     @Override  
     public void exceptionCaught(MsnMessenger messenger, Throwable throwable) {  
         msg(messenger + throwable.toString());  
         msg(throwable);  
     }  
   
     //��¼���ʱ����  
     @Override  
     public void loginCompleted(MsnMessenger messenger) {  
         msg(messenger.getOwner().getDisplayName() + "��¼�ɹ���");  
     //messenger.getOwner().setDisplayName("�����֮�Ժ�");  
     }  
   
     //ע��ʱ����  
     @Override  
     public void logout(MsnMessenger messenger) {  
         msg(messenger + " logout");  
     }  
   
     //�յ�ϵͳ�㲥��Ϣʱ����  
     @Override  
     public void datacastMessageReceived(MsnSwitchboard switchboard,  
             MsnDatacastMessage message, MsnContact friend) {  
         msg(switchboard + " recv datacast message " + message);  
         switchboard.sendMessage(message, false);  
     }  
   
     //�յ�Ŀǰ���ܴ������Ϣʱ����  
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
      * ���º����б����ʱ���� 
      */  
     @Override  
     public void contactListSyncCompleted(MsnMessenger messenger) {  
         listContacts();  
     }  
   
     /** */  
     /** 
      * �ر�һ�����촰��ʱ���� 
      */  
     @Override  
     public void switchboardClosed(MsnSwitchboard switchboard) {  
         msg("switchboardStarted " + switchboard);  
     }  
   
     /** */  
     /** 
      * ��һ�����촰��ʱ���� 
      */  
     @Override  
     public void switchboardStarted(MsnSwitchboard switchboard) {  
         msg("switchboardStarted " + switchboard);  
     }  
   
     //��ӡjϵ��  
     private void listContacts() {  
         MsnContact[] cons = messenger.getContactList().getContactsInList(MsnList.AL);  
         if (cons.length == 0) {  
             msg("��");  
         } else {  
             msg("��������" + cons.length + "��jϵ��");  
         }  
         for (int i = 0; i < cons.length; i++) {  
             String personal = ((MsnContactImpl) cons[i]).getPersonalMessage().equals("")  
                     ? "��" : ((MsnContactImpl) cons[i]).getPersonalMessage();  
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