package com.pandita.finalgrade

import android.animation.ValueAnimator
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.pandita.finalgrade.adapters.SemesterAdapter
import com.pandita.finalgrade.repository.models.Semester
import com.pandita.finalgrade.ui.semesters.SemestersFragment
import java.time.LocalDateTime

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var floatingActionButton: FloatingActionButton
    private lateinit var navController: NavController
    private lateinit var currentFragment: String

    private var fragmentsMap = mapOf(
        FRAGMENT_HOME to R.id.nav_home,
        FRAGMENT_COURSES to R.id.nav_courses,
        FRAGMENT_SEMESTERS to R.id.nav_semesters
    )

    companion object {
        const val FRAGMENT_HOME = "Home"
        const val FRAGMENT_COURSES = "Courses"
        const val FRAGMENT_SEMESTERS = "Semesters"
    }

    private lateinit var mainListAdapter: SemesterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        floatingActionButton = findViewById(R.id.fab)
        floatingActionButton.setOnClickListener { view ->
            if (currentFragment == FRAGMENT_COURSES) {
                showAlertDialogAddCourse(view)
            } else if (currentFragment == FRAGMENT_SEMESTERS) {
                showAlertDialogAddSemester()
            }
        }

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        navController = findNavController(R.id.nav_host_fragment)
        navController.addOnDestinationChangedListener {
                navController: NavController, navDestination: NavDestination, bundle: Bundle? ->
            Toast.makeText(this, "mee" + navDestination.label, Toast.LENGTH_SHORT)
        }
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
            R.id.nav_home, R.id.nav_courses, R.id.nav_semesters
        ), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    fun hideFloatingActionButton() {
        floatingActionButton.visibility = View.INVISIBLE
    }

    fun showFloatingActionButton() {
        floatingActionButton.visibility = View.VISIBLE
    }

    fun setCurrentFragment(nameFragment: String) {
        currentFragment = nameFragment
    }

    private fun showAlertDialogAddCourse(view: View) {
        Snackbar.make(view, "Add course", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()
    }

    private fun showAlertDialogAddSemester() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_semester, null)
        val yearPicker = dialogView.findViewById(R.id.year_picker_semester) as NumberPicker
        yearPicker.minValue = 2000
        yearPicker.maxValue = 2050
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            yearPicker.value = LocalDateTime.now().year
        } else {
            yearPicker.value = 2020
        }
        val alertDialog = AlertDialog.Builder(this)
        .setView(dialogView)
        .show()
        val cancelButton = dialogView.findViewById(R.id.cancel_button) as Button
        cancelButton.setOnClickListener {
            alertDialog.dismiss()
        }
        val saveButton = dialogView.findViewById(R.id.save_button) as Button
        saveButton.setOnClickListener {
            val name = (dialogView.findViewById(R.id.input_name_semester) as TextInputEditText).text.toString()
            val description = (dialogView.findViewById(R.id.input_description_semester) as TextInputEditText).text.toString()
            val year = yearPicker.value
            var currentSemester = 0
            if ((dialogView.findViewById(R.id.switch_current_semester) as SwitchMaterial).isChecked) {
                currentSemester = 1
            }
            val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            val semestersFragment = navHostFragment.childFragmentManager.fragments[0] as SemestersFragment?
            (semestersFragment)?.addSemester(Semester(
                0,
                name,
                description,
                year,
                currentSemester,
                R.color.colorPrimary,
                1
            ))
            alertDialog.dismiss()
        }
    }

}
