package org.danrecyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

interface AdapterItemListner<T> {
    void onResetListner();
}

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder>{

    private final LinkedList<String> mWordList;
    private LayoutInflater mInflater;

    //생성자를 통해 데이터 리스트 객체 전달받음
    public WordListAdapter(Context context,LinkedList<String> mWordList){
        this.mWordList = mWordList;
        mInflater = LayoutInflater.from(context);
    }

    // onCreateViewHolder() -> 아이템 뷰를 위한 뷰 홀더 객체 생성하여 리턴
    @NonNull
    @Override
    public WordListAdapter.WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //아이템 하나에 해당하는 뷰를 인플레이션
        View mItemView = mInflater.inflate(R.layout.wordlist_item, parent, false);

        return new WordViewHolder(mItemView, this);
    }

    // onBindViewHolder -> position에 해당하는 데이터를 뷰 홀더가 가지고있는 아이템뷰에 적용
    @Override
    public void onBindViewHolder(@NonNull WordListAdapter.WordViewHolder holder, int position) {
        String mCurrent = mWordList.get(position);
        holder.wordItemView.setText(mCurrent);
    }

    // getItemCount() -> 전체 아이템 개수 반환
    @Override
    public int getItemCount() {
        return mWordList.size();
    }

    // 아이템 뷰를 저장하는 뷰 홀더 클래스
    public class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView wordItemView;
        final WordListAdapter mAdapter;


        public WordViewHolder(@NonNull View itemView, WordListAdapter mAdapter) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.word);
            this.mAdapter = mAdapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            // Get the position of the item that was clicked.
            int mPosition = getLayoutPosition();

            // Use that to access the affected item in mWordList.
            String element = mWordList.get(mPosition);

            // Change the word in the mWordList.
            mWordList.set(mPosition, "Clicked! " + element);

            // Notify the adapter that the data has changed so it can
            // update the RecyclerView to display the data.
            mAdapter.notifyDataSetChanged();
        }
    }
}
