package com.example.dishank.madt_lab;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dishank on 3/4/2016.
 */
public class Tab2Fragment extends Fragment {

    private List<DataProvider> DList = new ArrayList<>();
    private RecyclerView recyclerView;
    //private List<String> List;
    private List<Integer> idList;
    //ListAdapter la;
    DBHelper dhelper;
    CustomListAdapter adapter;



    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab2fragment,container,false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.rView);

        adapter = new CustomListAdapter(DList);

        RecyclerView.LayoutManager dLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(dLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),LinearLayoutManager.VERTICAL));


        //List = new ArrayList<String>();
        idList = new ArrayList<Integer>();
        dhelper = new DBHelper(getActivity());
        dhelper.open();
        Cursor c = dhelper.getAllEntries();
        c.moveToFirst();
        for (int i =0; i<c.getCount();i++)
        {
            idList.add(c.getInt(0));
            DataProvider data = new DataProvider(c.getString(1),c.getString(2),c.getString(3));
            DList.add(data);
            //List.add(c.getString(1));
            c.moveToNext();
        }
        c.close();
        //adapter = new CustomListAdapter(getActivity(), R.layout.row_layout);
        //la = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_expandable_list_item_1,List);
        recyclerView.setAdapter(adapter);
        //lview.setOnItemClickListener(this);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new CliclListener() {
            @Override
            public void OnClick(View view, int position) {
                final int indexvalue = idList.get(position);
                dhelper = new DBHelper(getActivity());
                dhelper.open();
                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                dialog.setTitle("Delete");
                dialog.setMessage("Do you want to delete "+ DList.get(position) + " " + indexvalue);
                dialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        boolean result = dhelper.removeEntry(indexvalue);
                        dhelper.close();
                        onResume();
                    }
                });

                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        onResume();
                    }
                });

                dialog.create();
                dialog.show();
            }

            @Override
            public void OnLongClick(View view, int position) {

            }
        }));

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        dhelper = new DBHelper(getActivity());
        dhelper.open();
        idList.clear();
        DList.clear();
        Cursor c1 = dhelper.getAllEntries();
        c1.moveToFirst();
        for (int i =0; i<c1.getCount();i++)
        {
            idList.add(c1.getInt(0));
            DataProvider data1 = new DataProvider(c1.getString(1),c1.getString(2),c1.getString(3));
            DList.add(data1);
            c1.moveToNext();
        }
        recyclerView = (RecyclerView) recyclerView.findViewById(R.id.rView);
        dhelper.close();
        //adapter = new CustomListAdapter(getActivity(), R.layout.row_layout);
        //la = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_expandable_list_item_1,List);
        recyclerView.setAdapter(adapter);
        //lview.setOnItemClickListener(this);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new CliclListener() {
            @Override
            public void OnClick(View view, int position) {
                final int indexvalue = idList.get(position);
                dhelper = new DBHelper(getActivity());
                dhelper.open();
                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                dialog.setTitle("Delete");
                dialog.setMessage("Do you want to delete "+ DList.get(position) + " " + indexvalue);
                dialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        boolean result = dhelper.removeEntry(indexvalue);
                        dhelper.close();
                        onResume();
                    }
                });

                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        onResume();
                    }
                });

                dialog.create();
                dialog.show();
            }

            @Override
            public void OnLongClick(View view, int position) {

            }
        }));

    }

    public interface CliclListener{
        void OnClick(View view, int position);

        void OnLongClick(View view, int position);
    }

   /* @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        final int indexvalue = idList.get(position);
        dhelper = new DBHelper(getActivity());
        dhelper.open();
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle("Delete");
        dialog.setMessage("Do you want to delete "+ DList.get(position) + " " + indexvalue);
        dialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                boolean result = dhelper.removeEntry(indexvalue);
                dhelper.close();
                onResume();
            }
        });

        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                onResume();
            }
        });

        dialog.create();
        dialog.show();
    }*/




    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener{

        private GestureDetector gestureDetector;
        private Tab2Fragment.CliclListener cliclListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final Tab2Fragment.CliclListener cliclListener){
            this.cliclListener = cliclListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && cliclListener != null) {
                        cliclListener.OnLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }
}
