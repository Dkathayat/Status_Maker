package com.kathayat.myapplication

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import android.content.pm.PackageManager
import android.provider.MediaStore
import android.view.View
import android.widget.GridLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kathayat.myapplication.databinding.ActivityMainBinding
import com.recyclerviewapp.ImageAdapter
import java.lang.Exception


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var imageRecycler: RecyclerView? = null
    private var progressBar: ProgressBar? = null
    private var allPictures: ArrayList<Images>? = null

    companion object {
        private const val READ_PERMISSION_CODE = 100
        private const val STORAGE_PERMISSION_CODE = 101
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        imageRecycler?.LayoutManager = GridLayoutManager(this,3)
        imageRecycler?.setHasFixedSize(true)


        viewPagerAdapter.addFragment(Images(), "Images")
        viewPagerAdapter.addFragment(Videos(), "Videos")
        viewPagerAdapter.addFragment(MyStatus(), "My Status")


        binding.viewpager.adapter = viewPagerAdapter
        binding.tablayout.setupWithViewPager(binding.viewpager)

        checkPermission(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            STORAGE_PERMISSION_CODE
        )
        checkPermission(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            STORAGE_PERMISSION_CODE
        )

    }

    internal class ViewPagerAdapter(fragmentManager: FragmentManager) :
        FragmentPagerAdapter(fragmentManager) {

        private val fragments: ArrayList<Fragment>
        private val titles: ArrayList<String>

        init {
            fragments = ArrayList<Fragment>()
            titles = ArrayList<String>()
        }

        override fun getCount(): Int {
            return fragments.size
        }

        override fun getItem(position: Int): Fragment {
            return fragments[position]
        }

        fun addFragment(fragment: Fragment, title: String) {
            fragments.add(fragment)
            titles.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return titles[position]
        }
    }

    private fun checkPermission(permission: String, requestCode: Int) {
        if (ContextCompat.checkSelfPermission(
                this@MainActivity,
                permission
            ) == PackageManager.PERMISSION_DENIED
        ) {

            // Requesting the permission
            ActivityCompat.requestPermissions(this@MainActivity, arrayOf(permission), requestCode)
        } else {
        }
    }

    // This function is called when the user accepts or decline the permission.
    // Request Code is used to check which permission called this function.
    // This request code is provided when the user is prompt for permission.
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == READ_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this@MainActivity, "Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this@MainActivity, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        } else if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this@MainActivity, "Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this@MainActivity, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
        allPictures = ArrayList()
        if (allPictures!!.isEmpty()) {
            progressBar?.visibility = View.VISIBLE
            allPictures = allImages()
            imageRecycler?.adapter = ImageAdapter(this, allPictures!!)
            progressBar?.visibility = View.GONE

        }
    }


    }
}

//    private fun hasWriteExternalStoragePermission() = ActivityCompat.checkSelfPermission(
//        this,
//        Manifest.permission.WRITE_EXTERNAL_STORAGE
//    ) == PackageManager.PERMISSION_GRANTED
//
//    private fun hasReadExternalStoragePermission() = ActivityCompat.checkSelfPermission(
//        this,
//        Manifest.permission.READ_EXTERNAL_STORAGE
//    ) == PackageManager.PERMISSION_GRANTED
//
//    private fun requestPermission() {
//        val permissionToRequest = mutableListOf<String>()
//        if (hasWriteExternalStoragePermission()) {
//            permissionToRequest.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
//        }
//        if (hasReadExternalStoragePermission()) {
//            permissionToRequest.add(Manifest.permission.READ_EXTERNAL_STORAGE)
//        }
//
//        if (permissionToRequest.isNotEmpty()) {
//            ActivityCompat.requestPermissions(this, permissionToRequest.toTypedArray(), 0)
//        }
//    }
//
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//            if(requestCode == 0 && grantResults.isNotEmpty()) {
//                for (i in grantResults.indices){
//                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
//                        Log.d("Permission Request", "${permissions[i]} granted ")
//                    }
//                }
//            }
//    }








