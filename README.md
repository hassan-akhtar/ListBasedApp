<H1>Answers:</H1>

<B>1-</B> An interface is a description of the behaviour an implementing class will have.
   The implementing class ensures, that it will have these methods that can be used on it.
   It is basically a contract or a promise the class has to make.

   An abstract class is a basis for different subclasses that share behaviour which does not
   need to be repeatedly be created. Subclasses must complete the behaviour and have the option
   to override predefine behaviour (as long as it is not defined as final or private).

<B>Example :

     public interface LoginAuth {
           public String encryptPassword(String pass);
           public void checkDBforUser();
                  }

   Now suppose you have 3 databases in your application. Then each and every implementation for 
   that database needs to define the above 2 methods:


        public class DBMySQL implements LoginAuth {
          // Needs to implement both methods
        }
        public class DBOracle implements LoginAuth {
          // Needs to implement both methods
        }
       public class DBAbc implements LoginAuth {
          // Needs to implement both methods
       }

     But what if encryptPassword() is not database dependent, 
     and it's the same for each class? Then the above would not be a good approach.

        Instead, consider this approach:

            public abstract class LoginAuth{
            public String encryptPassword(String pass){
            // Implement the same default behavior here 
            // that is shared by all subclasses.
            }

             // Each subclass needs to provide their own implementation of this only:
             public abstract void checkDBforUser();
               }
 
     Now in each child class, we only need to implement one method - the method that is database dependent.

------------------------------------------------------------------------------------------------------------------------------------------

<B>2-</B>    It flawed in Multiple inheritance (Diamond Problem). Suppose We have two classes B and C inheriting from A. 
      Assume that B and C are overriding an inherited method and they provide their own implementation. 
      Now D inherits from both B and C doing multiple inheritance. D should inherit that overridden method, 
      which overridden method will be used? Will it be from B or C? Here we have an ambiguity. 

------------------------------------------------------------------------------------------------------------------------------------------

<B>3-</B>    An Activity is an application component that provides a screen, with which users can interact in order
      to do something whereas a Fragment represents a behavior or a portion of user interface in an Activity.

------------------------------------------------------------------------------------------------------------------------------------------

<B>4-</B>    To allow a Fragment to communicate up to its Activity, you can define an interface in the Fragment class and implement
      it within the Activity. The Fragment captures the interface implementation during its onAttach() lifecycle method and
      can then call the Interface methods in order to communicate with the Activity.

       Here is an example of Fragment to Activity communication:

    public class HeadlinesFragment extends ListFragment {
    OnHeadlineSelectedListener mCallback;

    // Container Activity must implement this interface
    public interface OnHeadlineSelectedListener {
        public void onArticleSelected(int position);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnHeadlineSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    ...
}

      Now the fragment can deliver messages to the activity by calling the onArticleSelected() method 
      (or other methods in the interface) using the mCallback instance of the OnHeadlineSelectedListener interface.

     For example, the following method in the fragment is called when the user clicks on a list item. 
     The fragment uses the callback interface to deliver the event to the parent activity.

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // Send the event to the host activity
        mCallback.onArticleSelected(position);
    }

       In order to receive event callbacks from the fragment, the activity that hosts 
       it must implement the interface defined in the fragment class.
       For example, the following activity implements the interface from the above example.

public static class MainActivity extends Activity
        implements HeadlinesFragment.OnHeadlineSelectedListener{
    ...

    public void onArticleSelected(int position) {
        // The user selected the headline of an article from the HeadlinesFragment
        // Do something here to display that article
    }
}

------------------------------------------------------------------------------------------------------------------------------------------

<B>5-</B>   Activity is a part of the four core components of Android, along with Service, Broadcast Receiver and Content provider.
     But fragment is not a core component, it's there to be used as a part or 'fragment' of activity. Your app certainly can 
     be built using only activities, just like many other apps.

------------------------------------------------------------------------------------------------------------------------------------------

<B>6-</B>   No orientation change support
            No ability to cancel network calls
            As well as no easy way to make API calls in parallel.

     Let take an example of Orientation change issue.

      One problem with configuration changes and the destroy-and-create cycle that Activities go through.
      Activity starts an AsyncTask and soon after the user rotates the screen, causing the Activity to be destroyed and recreated
      so it is vital that we correctly and efficiently retain active objects across Activity instances when configuration changes occur.

      By default, Fragments are destroyed and recreated along with their parent Activitys when a configuration change occurs.
      Calling Fragment#setRetainInstance(true) allows us to bypass this destroy-and-recreate cycle, signaling the system to retain
      the current instance of the fragment when the activity is recreated. As we will see, this will prove to be extremely useful 
      with Fragments that hold objects like running Threads, AsyncTasks, Sockets, etc.
