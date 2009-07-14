using System;
using System.Collections.Generic;
using System.Text;

namespace UILib
{
    enum enButtonPresed
    {
        ButtonPhone,
        ButtonSMS
    }
    class InterceptButtonPressed : baseMessageReceiver
    {

        public InterceptButtonPressed() :base()
        {            
        }
        
        
            

        public override void OnReceiveMessage(myMessage e)
        {
         
            string sMsg;
            sMsg = "Message received Code: " + e._Code + " Text: " + e._Text + "\r\n";
            Console.WriteLine(sMsg);

            enButtonPresed ButtonCode = (enButtonPresed)e._Code;

            string MessaggioLog = string.Empty;
            switch (ButtonCode)
            {
                case enButtonPresed.ButtonPhone:
                    break;
                case enButtonPresed.ButtonSMS:
                    break;

            }
        }
    }
}
