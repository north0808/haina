﻿namespace TouchScrolling
{
    using Resco.Controls.OutlookControls;
    using System;
    using System.Drawing;
    using System.Runtime.CompilerServices;
    using System.Windows.Forms;

    internal class TouchTool
    {
        private bool m_bEnableTouchScrolling = true;
        private bool m_bTouchScrolling;
        private bool m_LeftRightGesture;
        private int m_MinX = 8;
        private int m_MinY = 8;
        private Point m_MousePosition = new Point(0, 0);
        private Roller m_Parent;
        private int m_TouchAutoScrollDiffX;
        private int m_TouchAutoScrollDiffY;
        private Timer m_TouchScrollingTimer;
        private static int m_touchSensitivity = 2;
        private int m_TouchTime0;
        private int m_TouchTime1;

        internal event GestureDetectedHandler GestureDetected;

        internal event MouseMoveDetectedHandler MouseMoveDetected;

        internal event EventHandler MouseMoveEnded;

        public TouchTool(Roller aParent)
        {
            this.m_Parent = aParent;
            this.m_TouchScrollingTimer = new Timer();
            this.m_TouchScrollingTimer.Enabled = false;
            this.m_TouchScrollingTimer.Interval = 1;
            this.m_TouchScrollingTimer.Tick += new EventHandler(this.OnTouchScrollingTimerTick);
            this.m_bTouchScrolling = false;
            this.m_TouchAutoScrollDiffY = 0;
            this.m_TouchAutoScrollDiffX = 0;
            this.m_LeftRightGesture = false;
        }

        public void Dispose()
        {
            if (this.m_TouchScrollingTimer != null)
            {
                this.m_TouchScrollingTimer.Tick -= new EventHandler(this.OnTouchScrollingTimerTick);
                this.m_TouchScrollingTimer.Dispose();
                this.m_TouchScrollingTimer = null;
            }
        }

        private void HandleLeftRightScrolling()
        {
            if (this.m_TouchAutoScrollDiffX < 0)
            {
                this.OnMouseMoveDetected(GestureType.Left, -this.m_TouchAutoScrollDiffX, 0);
                this.m_TouchAutoScrollDiffX += (Math.Abs(this.m_TouchAutoScrollDiffX) / 10) + 1;
                if (this.m_TouchAutoScrollDiffX > 0)
                {
                    this.m_TouchAutoScrollDiffX = 0;
                    this.m_TouchScrollingTimer.Enabled = false;
                    this.OnMouseMoveEnded();
                }
            }
            else if (this.m_TouchAutoScrollDiffX > 0)
            {
                this.OnMouseMoveDetected(GestureType.Right, -this.m_TouchAutoScrollDiffX, 0);
                this.m_TouchAutoScrollDiffX -= (Math.Abs(this.m_TouchAutoScrollDiffX) / 10) + 1;
                if (this.m_TouchAutoScrollDiffX < 0)
                {
                    this.m_TouchAutoScrollDiffX = 0;
                    this.m_TouchScrollingTimer.Enabled = false;
                    this.OnMouseMoveEnded();
                }
            }
            else
            {
                this.m_TouchScrollingTimer.Enabled = false;
                this.OnMouseMoveEnded();
            }
        }

        private void HandleUpDownScrolling()
        {
            if (this.m_TouchAutoScrollDiffY < 0)
            {
                this.OnMouseMoveDetected(GestureType.Up, 0, -this.m_TouchAutoScrollDiffY);
                this.m_TouchAutoScrollDiffY += (Math.Abs(this.m_TouchAutoScrollDiffY) / 10) + 1;
                if (this.m_TouchAutoScrollDiffY > 0)
                {
                    this.m_TouchAutoScrollDiffY = 0;
                    this.m_TouchScrollingTimer.Enabled = false;
                    this.OnMouseMoveEnded();
                }
            }
            else if (this.m_TouchAutoScrollDiffY > 0)
            {
                this.OnMouseMoveDetected(GestureType.Down, 0, -this.m_TouchAutoScrollDiffY);
                this.m_TouchAutoScrollDiffY -= (Math.Abs(this.m_TouchAutoScrollDiffY) / 10) + 1;
                if (this.m_TouchAutoScrollDiffY < 0)
                {
                    this.m_TouchAutoScrollDiffY = 0;
                    this.m_TouchScrollingTimer.Enabled = false;
                    this.OnMouseMoveEnded();
                }
            }
            else
            {
                this.m_TouchScrollingTimer.Enabled = false;
                this.OnMouseMoveEnded();
            }
        }

        public void MouseDown(int aX, int aY)
        {
            this.m_MousePosition.X = aX;
            this.m_MousePosition.Y = aY;
            this.Reset();
        }

        public void MouseMove(int aX, int aY)
        {
            if (this.m_bEnableTouchScrolling)
            {
                int num = aY - this.m_MousePosition.Y;
                int num2 = aX - this.m_MousePosition.X;
                if ((Math.Abs(num) >= this.m_MinY) || (Math.Abs(num2) >= this.m_MinX))
                {
                    this.m_TouchAutoScrollDiffY += num;
                    this.m_TouchAutoScrollDiffX += num2;
                    if ((Environment.TickCount - this.m_TouchTime1) > 300)
                    {
                        this.m_TouchAutoScrollDiffY = 0;
                        this.m_TouchAutoScrollDiffX = 0;
                        this.m_TouchTime0 = Environment.TickCount;
                        this.m_TouchTime1 = this.m_TouchTime0;
                        this.m_bTouchScrolling = false;
                    }
                    else
                    {
                        this.m_TouchTime1 = Environment.TickCount;
                        this.m_bTouchScrolling = true;
                        this.m_MousePosition.X = aX;
                        this.m_MousePosition.Y = aY;
                        if (Math.Abs(this.m_TouchAutoScrollDiffX) < Math.Abs(this.m_TouchAutoScrollDiffY))
                        {
                            this.m_LeftRightGesture = false;
                        }
                        else
                        {
                            this.m_LeftRightGesture = true;
                        }
                        if (this.m_LeftRightGesture)
                        {
                            GestureType gestureType = (this.m_TouchAutoScrollDiffX > 0) ? GestureType.Left : GestureType.Right;
                            this.OnMouseMoveDetected(gestureType, -num2, -num);
                        }
                        else
                        {
                            GestureType type2 = (this.m_TouchAutoScrollDiffY > 0) ? GestureType.Up : GestureType.Down;
                            this.OnMouseMoveDetected(type2, -num2, -num);
                        }
                    }
                }
            }
        }

        public bool MouseUp(int aX, int aY)
        {
            bool flag = false;
            try
            {
                if (!this.m_bEnableTouchScrolling || !this.m_bTouchScrolling)
                {
                    return flag;
                }
                if ((Environment.TickCount - this.m_TouchTime1) > (300 * ((int) Roller.m_ScaleFactor.Height)))
                {
                    this.Reset();
                    return false;
                }
                if (Math.Abs(this.m_TouchAutoScrollDiffX) < Math.Abs(this.m_TouchAutoScrollDiffY))
                {
                    this.m_LeftRightGesture = false;
                }
                else
                {
                    this.m_LeftRightGesture = true;
                }
                if (this.m_LeftRightGesture)
                {
                    int num = (Environment.TickCount - this.m_TouchTime1) / 10;
                    if (num > 0)
                    {
                        this.m_TouchAutoScrollDiffX /= num;
                    }
                    this.m_TouchAutoScrollDiffX *= (int) Roller.m_ScaleFactor.Width;
                    this.m_TouchScrollingTimer.Enabled = true;
                }
                else
                {
                    int num2 = (Environment.TickCount - this.m_TouchTime1) / 10;
                    if (num2 > 0)
                    {
                        this.m_TouchAutoScrollDiffY /= num2;
                    }
                    this.m_TouchAutoScrollDiffY *= (int) Roller.m_ScaleFactor.Height;
                    this.m_TouchScrollingTimer.Enabled = true;
                }
                flag = true;
                this.m_bTouchScrolling = false;
            }
            catch (ObjectDisposedException)
            {
            }
            return flag;
        }

        private void OnMouseGestureDetected(GestureType gestureType)
        {
            if (this.GestureDetected != null)
            {
                GestureEventArgs e = new GestureEventArgs(gestureType);
                this.GestureDetected(this, e);
            }
        }

        private void OnMouseMoveDetected(GestureType gestureType, int aMoveX, int aMoveY)
        {
            if (this.MouseMoveDetected != null)
            {
                MouseMoveEventArgs e = new MouseMoveEventArgs(gestureType, aMoveX, aMoveY);
                this.MouseMoveDetected(this, e);
            }
        }

        private void OnMouseMoveEnded()
        {
            if (this.MouseMoveEnded != null)
            {
                this.MouseMoveEnded(this, EventArgs.Empty);
            }
        }

        private void OnTouchScrollingTimerTick(object sender, EventArgs e)
        {
            if ((this.m_Parent != null) && this.m_Parent.IsDisposed)
            {
                this.StopTouchScrollingTimer();
            }
            else if (this.m_LeftRightGesture)
            {
                this.HandleLeftRightScrolling();
            }
            else
            {
                this.HandleUpDownScrolling();
            }
        }

        public void Reset()
        {
            if (this.m_bEnableTouchScrolling)
            {
                this.m_TouchScrollingTimer.Enabled = false;
                this.m_TouchAutoScrollDiffY = 0;
                this.m_TouchAutoScrollDiffX = 0;
                this.m_LeftRightGesture = false;
                this.m_TouchTime0 = Environment.TickCount;
                this.m_TouchTime1 = this.m_TouchTime0;
                this.m_bTouchScrolling = false;
            }
        }

        internal void StopTouchScrollingTimer()
        {
            this.m_TouchScrollingTimer.Enabled = false;
        }

        public bool EnableTouchScrolling
        {
            get
            {
                return this.m_bEnableTouchScrolling;
            }
            set
            {
                this.m_bEnableTouchScrolling = value;
            }
        }

        public Roller ParentControl
        {
            get
            {
                return this.m_Parent;
            }
            set
            {
                this.m_Parent = value;
            }
        }

        public int TouchSensitivity
        {
            get
            {
                return m_touchSensitivity;
            }
            set
            {
                m_touchSensitivity = value;
                this.m_MinX = m_touchSensitivity;
                this.m_MinY = m_touchSensitivity;
            }
        }

        internal delegate void GestureDetectedHandler(object sender, TouchScrolling.TouchTool.GestureEventArgs e);

        internal class GestureEventArgs : EventArgs
        {
            private TouchScrolling.TouchTool.GestureType m_Gesture;

            public GestureEventArgs(TouchScrolling.TouchTool.GestureType aGesture)
            {
                this.m_Gesture = aGesture;
            }

            public TouchScrolling.TouchTool.GestureType Gesture
            {
                get
                {
                    return this.m_Gesture;
                }
            }
        }

        internal enum GestureType
        {
            Left,
            Right,
            Up,
            Down
        }

        internal delegate void MouseMoveDetectedHandler(object sender, TouchScrolling.TouchTool.MouseMoveEventArgs e);

        internal class MouseMoveEventArgs : EventArgs
        {
            private TouchScrolling.TouchTool.GestureType m_Gesture;
            private int m_MoveX;
            private int m_MoveY;

            public MouseMoveEventArgs(TouchScrolling.TouchTool.GestureType aGesture, int aMoveX, int aMoveY)
            {
                this.m_Gesture = aGesture;
                this.m_MoveX = aMoveX;
                this.m_MoveY = aMoveY;
            }

            public TouchScrolling.TouchTool.GestureType Gesture
            {
                get
                {
                    return this.m_Gesture;
                }
            }

            public int MoveX
            {
                get
                {
                    return this.m_MoveX;
                }
            }

            public int MoveY
            {
                get
                {
                    return this.m_MoveY;
                }
            }
        }
    }
}

