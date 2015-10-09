package cn.com.nbd.nbdmobile.fragment;

import java.util.ArrayList;
import java.util.List;

import org.hjh.async.framework.AppHandler;
import org.hjh.inject.InjectCore;
import org.hjh.inject.InjectLayout;
import org.hjh.inject.InjectView;
import org.hjh.refresh.PullToRefreshBase;
import org.hjh.refresh.PullToRefreshBase.OnRefreshListener;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;

import cn.com.nbd.nbdmobile.R;
import cn.com.nbd.nbdmobile.api.AppConstants;
import cn.com.nbd.nbdmobile.api.HomeComponent;

import cn.com.nbd.nbdmobile.bean.Article;
import cn.com.nbd.nbdmobile.bean.ArticleDetail;
import cn.com.nbd.nbdmobile.bean.StockDetail;
import cn.com.nbd.nbdmobile.config.AppPresences;
import cn.com.nbd.nbdmobile.holder.ArticleHolder;
import cn.com.nbd.nbdmobile.tool.BaseTools;
import cn.com.nbd.nbdmobile.view.PullToRefreshListView;

import com.dpt.base.AppPublicAdapter;

import com.dpt.base.AppPublicAdapter.IFillValue;

@InjectLayout(layout = R.layout.tab_client_layout)
public class CodeFragment extends BaseFragment implements IFillValue,
        OnItemClickListener {

    @InjectView(id = R.id.pullview)
    private PullToRefreshListView refreshView;
    private Context context;

    private ListView listView;
    private AppPublicAdapter adapter;
    private StockDetail detail;

    private List<ArticleDetail> list = new ArrayList<ArticleDetail>();
    private View rootLayout;
    private int currentPage = 1;
    private int pageSize = 10;
    private int index = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (rootLayout == null) {
            rootLayout = InjectCore.injectObject(this, getActivity(), true);
        }

        if (rootLayout.getParent() != null) {

            ViewGroup parent = (ViewGroup) rootLayout.getParent();

            if (parent != null) {
                parent.removeView(rootLayout);
            }
        }
        return rootLayout;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        init();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        HomeComponent.getInstance().queryStockInfo(mHandler);
        HomeComponent.getInstance().queryArticle(currentPage, pageSize,
                mHandler);
    }

    private void init() {

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        // mPosterPager.startAutoScroll();
        if (list.isEmpty()) {

            // HelperComponent.getInstance().queryClients("", "", "", "",
            // currentPage, pageSize, mHandler);
        }
    }

    private void initPullView(StockDetail msg) {
        LinearLayout.LayoutParams params = (LayoutParams) refreshView
                .getLayoutParams();
        int tabhight = AppPresences.getInstance().getInt("tabhight");
        int titlehight = AppPresences.getInstance().getInt("titlehight");
        params.height = BaseTools.getWindowHight(mActivity) - titlehight - tabhight - getStatusBarHeight();
        refreshView.setPullLoadEnabled(false);
        refreshView.setScrollLoadEnabled(true);
        listView = refreshView.getRefreshableView();

        View header = (View) getLayoutInflater(null).inflate(
                R.layout.article_top_list_item, null);
        TextView shangId = (TextView) header.findViewById(R.id.shang_id);
        TextView shenId = (TextView) header.findViewById(R.id.shen_id);
        TextView hengId = (TextView) header.findViewById(R.id.heng_id);

        TextView shangRate = (TextView) header.findViewById(R.id.shang_rate);
        TextView shenRate = (TextView) header.findViewById(R.id.shen_rate);
        TextView hengRate = (TextView) header.findViewById(R.id.heng_rate);

        shangId.setText(msg.getRetData().getMarket().getShanghai().getCurdot()
                + "");
        shenId.setText(msg.getRetData().getMarket().getShenzhen().getCurdot()
                + "");
        hengId.setText(msg.getRetData().getMarket().getHSI().getCurdot() + "");

        shangRate.setText(msg.getRetData().getMarket().getShanghai()
                .getCurprice()
                + " "
                + msg.getRetData().getMarket().getShanghai().getRate()
                + "%");
        shenRate.setText(msg.getRetData().getMarket().getShenzhen()
                .getCurprice()
                + " "
                + msg.getRetData().getMarket().getShenzhen().getRate()
                + "%");
        hengRate.setText(msg.getRetData().getMarket().getHSI().getCurprice()
                + " " + msg.getRetData().getMarket().getHSI().getRate() + "%");

        if (msg.getRetData().getMarket().getShanghai().getCurprice() < 0) {
            shangId.setTextColor(getResources().getColor(R.color.green));
            shangRate.setTextColor(getResources().getColor(R.color.green));
        }

        if (msg.getRetData().getMarket().getShenzhen().getCurprice() < 0) {
            shenId.setTextColor(getResources().getColor(R.color.green));
            shenRate.setTextColor(getResources().getColor(R.color.green));
        }

        if (msg.getRetData().getMarket().getHSI().getCurprice() < 0) {
            hengId.setTextColor(getResources().getColor(R.color.green));
            hengRate.setTextColor(getResources().getColor(R.color.green));
        }
        listView.addHeaderView(header);
        adapter = new AppPublicAdapter(mActivity, list, ArticleHolder.class,
                this);
        listView.setAdapter(adapter);
        listView.setDivider(getResources().getDrawable(R.color.gray));
        listView.setCacheColorHint(Color.TRANSPARENT);

        refreshView.setOnRefreshListener(new OnRefreshListener<ListView>() {

            @Override
            public void onPullDownToRefresh(
                    PullToRefreshBase<ListView> refreshView) {
                adapter.clear();//
                currentPage = 1;
                mHandler.showDialog(false);
                HomeComponent.getInstance().queryArticle(currentPage, pageSize,
                        mHandler);
            }

            @Override
            public void onPullUpToRefresh(
                    PullToRefreshBase<ListView> refreshView) {
                mHandler.showDialog(false);
                HomeComponent.getInstance().queryArticle(currentPage, pageSize,
                        mHandler);
            }
        });

        setLastUpdateTime(refreshView);
        listView.setOnItemClickListener(this);
    }

    private void loadArticle(List<ArticleDetail> list) {

        adapter.getList().addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected AppHandler getAppHandler() {
        return new AppHandler(mActivity) {

            @Override
            public void disposeMessage(Message msg) {
                switch (msg.what) {
                    // case HelperConstants.RESULT_QUERY_CUSTOMER_LIST_FAILED:
                    // if(!isConnectTimeOut((ResultObject) msg.obj, "��ȡ�ͻ��б�")){}
                    // refreshView.onPullDownRefreshComplete();
                    // refreshView.onPullUpRefreshComplete();
                    // refreshView.setHasMoreData(false);
                    // setLastUpdateTime(refreshView);
                    // break;
                    case AppConstants.RESULT_QUERY_ARTICLE_SUCCESS:
                        currentPage++;
                        refreshView.onPullDownRefreshComplete();
                        refreshView.onPullUpRefreshComplete();
                        refreshView.setHasMoreData(true);
                        setLastUpdateTime(refreshView);
                        Article article = (Article) msg.obj;
                        loadArticle(article.getArticles());
                        break;
                    case AppConstants.RESULT_QUERY_STOCK_CONTENT_SUCCESS:
                        detail = (StockDetail) msg.obj;
                        initPullView(detail);
                        break;
                    default:
                        break;
                }
            }

        };
    }

    @Override
    public void fillData(int position, Object object) {
        ArticleHolder holder = (ArticleHolder) object;
        ArticleDetail articleListDetail = (ArticleDetail) adapter
                .getList().get(position);
        // holder.oneImageView.setImageDrawable(getResources().getDrawable(R.drawable.phone_icon));
        holder.description.setText(articleListDetail.getTitle());
        holder.readnum.setText(articleListDetail.getMobile_click_count());
        mImageLoader.loadImage(0, articleListDetail.getImage(), listener,
                holder.image, R.drawable.all_circle, 300, 300);
        // holder.threeView.setText(customer.getDescription());
        // holder.fourImageView.setImageDrawable(getResources().getDrawable(R.drawable.client_phone));

    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                            long arg3) {
        Intent intent = new Intent();
        // intent.setClass(mActivity, CustomerActivity.class);
        // HelperCustomer customer = (HelperCustomer)
        // adapter.getList().get(position);
        // Bundle bundle = new Bundle();
        // bundle.putSerializable("customer", customer);
        // intent.putExtras(bundle);
        // this.startActivity(intent);

    }

    @Override
    protected void lazyLoad() {
        // TODO Auto-generated method stub

    }

}
