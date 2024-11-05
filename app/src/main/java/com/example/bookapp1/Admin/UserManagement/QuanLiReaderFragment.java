package com.example.bookapp1.Admin.UserManagement;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.bookapp1.Admin.NovelManagement.QuanLiSach_Adapter;
import com.example.bookapp1.Admin.NovelManagement.QuanLiSach_Fragment;
import com.example.bookapp1.DTOs.BaseItem;
import com.example.bookapp1.DTOs.RvItem_QuanliSach;
import com.example.bookapp1.Models.Reader;
import com.example.bookapp1.Novel_Add_Edit_Activity;
import com.example.bookapp1.R;

import java.util.ArrayList;
import java.util.List;

public class QuanLiReaderFragment extends Fragment {
    private RecyclerView rV_quanlireader;
    private QuanLiReader_Adapter adapterDanhSachReader;
    private List<Reader> itemListReader;

    public static QuanLiReaderFragment newInstance(String content) {
        QuanLiReaderFragment fragment = new QuanLiReaderFragment();
        Bundle args = new Bundle();
        args.putString("novel_content", content); // Pass the content
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quan_li_reader, container, false);

        // Initialize RecyclerView
        rV_quanlireader = view.findViewById(R.id.rv_quanlireader);
        LinearLayoutManager layoutManagerQuanLiSach = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rV_quanlireader.setLayoutManager(layoutManagerQuanLiSach);

        // Sample data for Reader RecyclerView
        itemListReader = new ArrayList<>();
        itemListReader.add(new Reader(1, "john_doe", "password123", "johndoe@example.com", "1234567890", "active", "user"));
        itemListReader.add(new Reader(2, "jane_doe", "password456", "janedoe@example.com", "0987654321", "inactive", "admin"));
        itemListReader.add(new Reader(3, "mark_smith", "password789", "marksmith@example.com", "1122334455", "active", "user"));
        itemListReader.add(new Reader(4, "anna_jones", "password101", "annajones@example.com", "5566778899", "active", "moderator"));
        itemListReader.add(new Reader(5, "paul_martin", "password112", "paulmartin@example.com", "6677889900", "inactive", "user"));
        itemListReader.add(new Reader(6, "lucy_davis", "password131", "lucydavis@example.com", "7788990011", "active", "admin"));
        itemListReader.add(new Reader(7, "tom_brown", "password415", "tombrown@example.com", "8899001122", "active", "user"));
        itemListReader.add(new Reader(8, "susan_wilson", "password161", "susanwilson@example.com", "9900112233", "inactive", "user"));
        itemListReader.add(new Reader(9, "david_moore", "password718", "davidmoore@example.com", "0011223344", "active", "moderator"));
        itemListReader.add(new Reader(10, "emily_jackson", "password920", "emilyjackson@example.com", "2233445566", "inactive", "admin"));

// Now this list can be passed to the adapter

        adapterDanhSachReader = new QuanLiReader_Adapter(itemListReader);
        rV_quanlireader.setAdapter(adapterDanhSachReader);

        return view;
    }
}