package com.pandita.finalgrade.ui.semesters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.pandita.finalgrade.MainActivity
import com.pandita.finalgrade.R
import com.pandita.finalgrade.adapters.SemesterAdapter
import com.pandita.finalgrade.repository.models.Semester

class SemestersFragment : Fragment() {

    private lateinit var semestersViewModel: SemestersViewModel
    private lateinit var recyclerSemesters: RecyclerView
    private lateinit var semesterAdapter: SemesterAdapter

}
