package br.globo.com.hackathonglobo2019.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import br.globo.com.hackathonglobo2019.ui.fragment.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity



class MainActivity : AppCompatActivity() {

    companion object {
        const val USE_CAMERA_PERMISSION_CODE = 101
        const val SCAN_CODE_REQUEST = 102
        const val SCAN_CODE_RESULT = "SCAN_CODE_RESULT"
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            br.globo.com.hackathonglobo2019.R.id.navigation_home -> {
                val fragment = HomeFragment.newInstance()
                when(currentFragment){
                    is HomeFragment-> { }
                    else-> replaceFragment(fragment, HomeFragment.TAG, false)
                }
                return@OnNavigationItemSelectedListener true
            }
            br.globo.com.hackathonglobo2019.R.id.navigation_my_team -> {
                return@OnNavigationItemSelectedListener true
            }
            br.globo.com.hackathonglobo2019.R.id.navigation_calendar -> {
                return@OnNavigationItemSelectedListener true
            }
            br.globo.com.hackathonglobo2019.R.id.navigation_tables -> {
                return@OnNavigationItemSelectedListener true
            }
            br.globo.com.hackathonglobo2019.R.id.navigation_interatividade -> {

                val intent =  Intent(this@MainActivity,ScanActivity::class.java )
                startActivityForResult(intent, SCAN_CODE_REQUEST)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }


    var currentFragment:Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(br.globo.com.hackathonglobo2019.R.layout.activity_main)
        setSupportActionBar(toolbar)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigation.selectedItemId = br.globo.com.hackathonglobo2019.R.id.navigation_home
    }


    fun replaceFragment(fragment: Fragment,
                        tag: String,
                        addToBackStack:Boolean) {

        currentFragment = fragment

        val transaction = supportFragmentManager.beginTransaction()


        transaction.setCustomAnimations(
            br.globo.com.hackathonglobo2019.R.anim.enter_from_right,
            br.globo.com.hackathonglobo2019.R.anim.exit_to_left,
            br.globo.com.hackathonglobo2019.R.anim.enter_from_left,
            br.globo.com.hackathonglobo2019.R.anim.exit_to_right
        )

        transaction.replace(br.globo.com.hackathonglobo2019.R.id.fragment_container, fragment)

        if(addToBackStack)
            transaction.addToBackStack(tag)

        transaction.commit()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(br.globo.com.hackathonglobo2019.R.menu.main_menu, menu)
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode){
            SCAN_CODE_REQUEST -> {

                if(resultCode == Activity.RESULT_OK){

                    data?.let {

                        val code = data.getStringExtra(SCAN_CODE_RESULT)

                        startActivity<PlayerVideosActivity>(
                            PlayerVideosActivity.PLAYER_NAME to code
                        )

                    }
                }
            }
        }
    }
}
