package aidlexample.kagura.com.androidaidl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @version $Rev$
 * @auther kagura
 * @time 2016/6/16.21:30
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateData $Author$
 * @updatedes ${TODO}
 */
public class BookManagerService extends Service {
    private static final String TAG = "BMS";
    private CopyOnWriteArrayList<Book> mbookList = new CopyOnWriteArrayList<Book>();
    private Binder mBinder = new IBookManager.Stub(){
        @Override
        public List<Book> getBookList() throws RemoteException {
            return mbookList;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            mbookList.add(book);
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        mbookList.add(new Book(1,"Android"));
        mbookList.add(new Book(2,"Ios"));
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
