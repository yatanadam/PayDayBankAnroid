package com.payday.kdogruer.view.base


import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.FrameLayout
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentManager
import com.google.android.material.navigation.NavigationView
import com.payday.kdogruer.R
import com.payday.kdogruer.core.BaseFragment
import com.payday.kdogruer.utils.GenericHelper
import java.io.Serializable


/**
 * Created by kaandogruer on 24.4.2018.
 */
abstract class BaseActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    //region Views
    lateinit var drawerLayout: DrawerLayout
    private var mNavigationView: NavigationView? = null
    lateinit var flBaseContent: FrameLayout
    //endregion

    private var progressDialog: Dialog? = null


    public override fun onCreate(arg0: Bundle?) {
        super.onCreate(arg0)
        super.setContentView(R.layout.activity_base)
        findViewByIds()
        setListeners()
    }

    private fun setListeners() {
        mNavigationView!!.setNavigationItemSelectedListener(this)

    }

    private fun findViewByIds() {
        flBaseContent = findViewById<View>(R.id.ll_main_content) as FrameLayout
        drawerLayout = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        mNavigationView = findViewById<View>(R.id.nav_view) as NavigationView
    }


    override fun setContentView(id: Int) {
        val inflater = baseContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(id, flBaseContent)
    }


    fun showFullScreenLoading() {
        if (progressDialog == null) {
            progressDialog = Dialog(this).apply {
                requestWindowFeature(Window.FEATURE_NO_TITLE)
                setCancelable(false)
                setContentView(R.layout.loading_state)
                window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT)
            }
        }
        progressDialog?.show()
    }

    fun hideFullScreenLoading() {
        progressDialog?.let {
            if (it.isShowing) {
                it.cancel()
            }
        }
    }

    override fun onDestroy() {
        hideFullScreenLoading()
        super.onDestroy()
    }

    fun lockDrawer() {
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }

    /* override fun onClick(view: View) {

         when (view.id) {

             R.id.tv_additional_symptons -> {
                 startActivity(Intent(this,SearchActivity::class.java))
             }

         }
     }*/

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // set item as selected to persist highlight
        item.isChecked = true
        // close drawer when item is tapped
        drawerLayout.closeDrawers()
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                drawerLayout.openDrawer(GravityCompat.START)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    //region Fragment operations
    fun replaceFragment(fragment: BaseFragment, container: Int,
                        manager: FragmentManager): BaseFragment {
        GenericHelper.getInstance().hideKeyboard(this@BaseActivity)
        val transaction = manager.beginTransaction()
        //transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out,R.anim.fade_in, R.anim.fade_out);
        for (i in 0 until manager.backStackEntryCount) {
            manager.popBackStack()
        }
        transaction.replace(container, fragment).commit()
        return fragment
    }


    fun addFragment(fragment: BaseFragment, container: Int,
                    manager: FragmentManager, activeFragment: BaseFragment): BaseFragment {
        GenericHelper.getInstance().hideKeyboard(this@BaseActivity)
        val transaction = manager.beginTransaction()
        transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out)
        transaction.hide(activeFragment)
        transaction.add(container, fragment)
                .addToBackStack(null).commit()
        return fragment
    }

    fun replaceFragmentBackstack(fragment: BaseFragment, container: Int,
                                 manager: FragmentManager, activeFragment: BaseFragment) {
        var activeFragment = activeFragment
        GenericHelper.getInstance().hideKeyboard(this@BaseActivity)
        val transaction = manager.beginTransaction()
        transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out)
        transaction.replace(container, fragment)
                .addToBackStack(null).commit()
        activeFragment = fragment
    }

    fun replaceFragmentBackstackAndBundle(fragment: BaseFragment, bundleKey: String, `object`: Serializable,
                                          container: Int, manager: FragmentManager, activeFragment: BaseFragment) {
        var activeFragment = activeFragment
        val bundle = Bundle()
        bundle.putSerializable(bundleKey, `object`)
        fragment.arguments = bundle
        val transaction = manager.beginTransaction()
        transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out)
        transaction.replace(container, fragment)
                .addToBackStack(null).commit()
        activeFragment = fragment
    }

    //endregion

    fun showAlertDialog(context: Context, title: String, negativeButton: String,
                        negativeListener: DialogInterface.OnClickListener,
                        positiveButton: String, positiveListener: DialogInterface.OnClickListener,
                        message: String) {
        val alertDialog = AlertDialog.Builder(context)
                .setNegativeButton(negativeButton, negativeListener)
                .setPositiveButton(positiveButton, positiveListener).create()
        alertDialog.setTitle(title)
        alertDialog.setMessage(message)

        alertDialog.show()
    }

    fun showAlertDialog(context: Context, title: String,
                        positiveButton: String, positiveListener: DialogInterface.OnClickListener,
                        message: String) {
        val alertDialog = AlertDialog.Builder(context)
                .setPositiveButton(positiveButton, positiveListener).create()
        alertDialog.setTitle(title)
        alertDialog.setMessage(message)

        alertDialog.show()
    }

    fun showAlertDialog(context: Context, title: String,
                        positiveButton: String, positiveListener: DialogInterface.OnClickListener,
                        message: String,
                        dismissListener: DialogInterface.OnDismissListener) {
        val alertDialog = AlertDialog.Builder(context)
                .setPositiveButton(positiveButton, positiveListener).create()
        alertDialog.setTitle(title)
        alertDialog.setMessage(message)
        alertDialog.setOnDismissListener(dismissListener)

        alertDialog.show()
    }

    fun showAlertDialog(context: Context, title: String, negativeButton: String,
                        negativeListener: DialogInterface.OnClickListener,
                        positiveButton: String, positiveListener: DialogInterface.OnClickListener,
                        message: String, cancelable: Boolean) {
        val alertDialog = AlertDialog.Builder(context)
                .setNegativeButton(negativeButton, negativeListener)
                .setPositiveButton(positiveButton, positiveListener).create()
        alertDialog.setTitle(title)
        alertDialog.setMessage(message)
        alertDialog.setCanceledOnTouchOutside(cancelable)
        alertDialog.setCancelable(cancelable)

        alertDialog.show()
    }

    fun showAlertDialog(context: Context, title: String,
                        positiveButton: String, positiveListener: DialogInterface.OnClickListener,
                        message: String, cancelable: Boolean) {

        val alertDialog = AlertDialog.Builder(context)
                .setPositiveButton(positiveButton, positiveListener).create()
        alertDialog.setTitle(title)
        alertDialog.setMessage(message)
        alertDialog.setCanceledOnTouchOutside(cancelable)
        alertDialog.setCancelable(cancelable)


        alertDialog.show()
    }
}
