  //adapterclass for progress
    public static class SimpleView extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private static final int TYPE_HEADER = 0;
        private static final int TYPE_FOOTER = 1;
        private static final int TYPE_ITEM = 2;
        Activity context;
        List<Integer> list;
        int current;
        int width;

        private SimpleView(Activity context, List<Integer> mMarker, int current, int count) {
            this.context = context;
            this.list = mMarker;
            this.current = current;
            this.width = count;
        }

        public void updateList( List<Integer> list, int current){
            this.list = list;
            this.current = current;
            notifyDataSetChanged();
        }

        @SuppressWarnings("ConstantConditions")
        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            if (viewType == TYPE_ITEM) {
                //Inflating recycle view item layout
                View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycle, parent, false);
                return new ItemViewHolder(itemView);
            } else if (viewType == TYPE_HEADER) {
                //Inflating header view
                View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_recycle, parent, false);
                return new HeaderViewHolder(itemView);
            } else if (viewType == TYPE_FOOTER) {
                //Inflating footer view
                View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fooder_recycle, parent, false);
                return new FooterViewHolder(itemView);
            } else return null;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof HeaderViewHolder) {
                HeaderViewHolder headerHolder = (HeaderViewHolder) holder;
                headerHolder.header.getLayoutParams().width = width;
                headerHolder.header.requestLayout();
                Logger.logError("RewardM","with :" + width);
                if(list.size() == 1){
                    if(current == 1){
                        headerHolder.v.setBackground(null);
                        headerHolder.v.setBackground(ContextCompat.getDrawable(context, R.drawable.reward_center_blue));
                    }else{
                        headerHolder.v.setBackground(null);
                        headerHolder.v.setBackground(ContextCompat.getDrawable(context, R.drawable.reward_center_grey));
                    }
                }else{
                    if (position < current) {
                        headerHolder.v.setBackground(null);
                        headerHolder.v.setBackground(ContextCompat.getDrawable(context, R.drawable.header_bg));
                    }
                }
            } else if (holder instanceof FooterViewHolder) {
                FooterViewHolder footerHolder = (FooterViewHolder) holder;
                footerHolder.fooder.getLayoutParams().width = width;
                footerHolder.fooder.requestLayout();
                if (position < current) {
                    footerHolder.v.setBackground(null);
                    footerHolder.v.setBackground(ContextCompat.getDrawable(context, R.drawable.fooder_fill_bg));
                }
            } else if (holder instanceof ItemViewHolder) {
                ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
                itemViewHolder.item.getLayoutParams().width = width;
                itemViewHolder.item.requestLayout();
                if (position < current) {
                    itemViewHolder.v.setBackgroundColor(0xFF0047BB);
                }
            }
        }

        @Override
        public int getItemViewType(int position) {
            if (position == 0) {
                return TYPE_HEADER;
            } else if (position == list.size() - 1) {
                return TYPE_FOOTER;
            }
            return TYPE_ITEM;
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        private static class HeaderViewHolder extends RecyclerView.ViewHolder {
            View v;
            LinearLayout header;

            private HeaderViewHolder(View view) {
                super(view);
                v = view.findViewById(R.id.header);
                header = view.findViewById(R.id.header_linear);
            }
        }

        private static class FooterViewHolder extends RecyclerView.ViewHolder {
            View v;
            LinearLayout fooder;

            private FooterViewHolder(View view) {
                super(view);
                v = view.findViewById(R.id.fooder);
                fooder = view.findViewById(R.id.fooder_linaer);
            }
        }

        private static class ItemViewHolder extends RecyclerView.ViewHolder {
            View v;
            LinearLayout item;

            private ItemViewHolder(View itemView) {
                super(itemView);
                v = itemView.findViewById(R.id.item);
                item = itemView.findViewById(R.id.item_linaer);
            }
        }
    }
