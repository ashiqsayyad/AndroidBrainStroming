package  com.example.remoteservice_calculator;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class ArithmeticService extends Service{
    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i("Ashiq","========== ArithmeticService onUnbinded================");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.i("Ashiq","========== ArithmeticService destroyed================");
        super.onDestroy();
    }

    /*
    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }*/

    /**
     * IRemote definition is available here
     */
    private final IRemote.Stub mBinder = new IRemote.Stub() {

        @Override
        public int add(final int a, final int b) throws RemoteException {
            // TODO Auto-generated method stub
             int result =0;
            try {
                Log.i("Ashiq","sleeping remoteservic::");
                Thread.sleep(10000);
            }catch(Exception e){
                Log.i("Ashiq","Exceptoion in remoteservic::");
            }
            Log.i("Ashiq","returning a+b remoteservic::");

            return a+b;

        }
        @Override
        public int subtract(int a, int b)
                throws RemoteException {
            // TODO Auto-generated method stub
            return (a - b);
        }
        @Override
        public double multiply(int a, int b) throws RemoteException
        {
            // TODO Auto-generated method stub
            return (a * b);
        }
    };
}