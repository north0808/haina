using System;
using System.Collections.Generic;
using System.Text;

namespace UILib
{
    public delegate void MessageDelegate(myMessage message);

    // Messaggio da inviare
    public class myMessage
    {
        public int _Code;
        public string _Text;
        public myMessage(int code, string text)
        {
            this._Code = code;
            this._Text = text;
        }

        public myMessage(int code)
            : this(code, string.Empty) { }
    }

    // Classe di gestione messaggi. Invia il messaggio ai Receiver
    public class MessageService
    {
        public event MessageDelegate NewEventMessage;

        public void SendMessage(int code, string text)
        {
            if (NewEventMessage != null)
            {
                // This will send an instance of News to each subscriber 
                NewEventMessage(new myMessage(code, text));
            }
        }

        public void SendMessage(int code)
        {
            SendMessage(code, string.Empty);
        }

        public void SendMessage(myMessage message)
        {
            if (NewEventMessage != null)
            {
                // This will send an instance of News to each subscriber of local news
                NewEventMessage(message);
            }
        }
    }
    
    public class baseMessageSender
    {
        MessageService _messageservice;
        public baseMessageSender() { }

        public void SendMessage(int code, string text)
        {
            _messageservice.SendMessage(code, text);
        }

        public void SendMessage(int code)
        {
            _messageservice.SendMessage(code, string.Empty);
        }

        public void SetService(MessageService ms)
        {
            _messageservice = ms;
        }
    }

    public class baseMessageReceiver
    {
        public MessageService DefaultMessageService = new MessageService();

        public baseMessageReceiver()
        {
            DefaultMessageService.NewEventMessage += new MessageDelegate(OnReceiveMessage);
        }

        public virtual void OnReceiveMessage(myMessage e)
        {
            Console.WriteLine("MessageReceiver received: " + e._Text);
        }
    }
}
