package com.hdz.base.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.hdz.base.R
import com.hdz.base.adapter.ImgPagerAdapter
import com.hdz.base.util.L

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_TYPE = "type"
private const val ARG_LIST = "list"

/**
 * A simple [Fragment] subclass.
 * Use the [ViewPagerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ViewPagerFragment : Fragment() {
    // TODO: Rename and change types of parameters
    lateinit var type: String
    lateinit var list: ArrayList<String>
    lateinit var mAdapter: ImgPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            type = it.getString(ARG_TYPE)!!
            list = it.getStringArrayList(ARG_LIST)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val contentView = inflater.inflate(R.layout.fragment_view_pager, container, false)
        val viewpager: ViewPager = contentView.findViewById(R.id.viewpage_pager)
        mAdapter = ImgPagerAdapter(context, list)
        viewpager.adapter = mAdapter
        return contentView
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ViewPagerFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(type: String, list: ArrayList<String>) =
            ViewPagerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_TYPE, type)
                    putStringArrayList(ARG_LIST, list)
                }
            }
    }
}