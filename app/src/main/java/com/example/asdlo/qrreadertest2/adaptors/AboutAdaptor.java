package com.example.asdlo.qrreadertest2.adaptors;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asdlo.qrreadertest2.R;
import com.example.asdlo.qrreadertest2.model.AboutItem;
import com.example.asdlo.qrreadertest2.model.Footer;
import com.example.asdlo.qrreadertest2.model.Header;
import com.example.asdlo.qrreadertest2.model.RecyclerViewItem;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class AboutAdaptor extends RecyclerView.Adapter {

    List<RecyclerViewItem> recyclerViewItems;

    private static final int HEADER_ITEM = 0;

    private static final int FOOTER_ITEM = 1;

    private static final int ABOUT_ITEM = 2;

    Context mContext;

    public AboutAdaptor(List<RecyclerViewItem> recyclerViewItems, Context mContext) {
        this.recyclerViewItems = recyclerViewItems;
        System.out.println(recyclerViewItems.isEmpty());
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View row;
        if (viewType == HEADER_ITEM) {
            row = inflater.inflate(R.layout.about_header, parent, false);
            return new HeaderHolder(row);
        } else if (viewType == FOOTER_ITEM) {
            row = inflater.inflate(R.layout.about_footer, parent, false);
            return new FooterHolder(row);
        } else if (viewType == ABOUT_ITEM) {
            row = inflater.inflate(R.layout.about_item, parent, false);
            return new AboutItemHolder(row);

        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        RecyclerViewItem recyclerViewItem = recyclerViewItems.get(position);
        if (holder instanceof HeaderHolder) {
            HeaderHolder headerHolder = (HeaderHolder) holder;
            Header header = (Header) recyclerViewItem;
            System.out.println("11111111");
            headerHolder.texViewHeaderText.setText(header.getHeaderText());

        } else if (holder instanceof AboutItemHolder) {
            AboutItemHolder aboutItemHolder = (AboutItemHolder) holder;
            AboutItem aboutItem = (AboutItem) recyclerViewItem;
            System.out.println("3333333333");
            aboutItemHolder.texViewAboutTitle.setText(aboutItem.getTitle());
            aboutItemHolder.textViewAboutUserId.setText(String.valueOf(aboutItem.getUserId()));
            aboutItemHolder.texViewAboutId.setText(String.valueOf(aboutItem.getId()));
        } else if (holder instanceof FooterHolder) {
            FooterHolder footerHolder = (FooterHolder) holder;
            Footer footer = (Footer) recyclerViewItem;
            System.out.println("222222222");

            footerHolder.texViewQuote.setText(footer.getFooterText());

        }
    }

    @Override
    public int getItemViewType(int position) {

        RecyclerViewItem recyclerViewItem = recyclerViewItems.get(position);

        if (recyclerViewItem instanceof Header)
            return HEADER_ITEM;

        else if (recyclerViewItem instanceof Footer)
            return FOOTER_ITEM;

        else if (recyclerViewItem instanceof AboutItem)
            return ABOUT_ITEM;

        else
            return super.getItemViewType(position);

    }

    @Override
    public int getItemCount() {
        return recyclerViewItems.size();
    }

    private class AboutItemHolder extends RecyclerView.ViewHolder {
        TextView texViewAboutTitle, texViewAboutId, textViewAboutUserId;

        AboutItemHolder(View itemView) {
            super(itemView);
            texViewAboutTitle = itemView.findViewById(R.id.textViewAboutTitle);
            texViewAboutId = itemView.findViewById(R.id.textViewAboutId);
            textViewAboutUserId = itemView.findViewById(R.id.textViewAboutUserId);

        }
    }

    private class HeaderHolder extends RecyclerView.ViewHolder {
        TextView texViewHeaderText;

        HeaderHolder(View itemView) {
            super(itemView);
            texViewHeaderText = itemView.findViewById(R.id.texViewHeaderText);
        }
    }

    private class FooterHolder extends RecyclerView.ViewHolder {
        TextView texViewQuote;

        FooterHolder(View itemView) {
            super(itemView);
            texViewQuote = itemView.findViewById(R.id.textViewTitle);
        }
    }
}