#retrofit实现
 Network.getDemo().getNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<News>() {
                               @Override
                               public void onCompleted() {
                                   Toast.makeText(MainActivity.this,"onCompleted", Toast.LENGTH_SHORT).show();

                               }

                               @Override
                               public void onError(Throwable e) {
                                   Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                               }

                               @Override
                               public void onNext(News news) {
                                   adapter.setDatas(news.getTop_stories());
                               }
                           }
                );
              
			  判断			
网络状态
/**
	 * 判断网络是否可用
	 * @param context
	 * @return
	 */
	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (cm != null) {
			//如果仅仅是用来判断网络连接
			//则可以使用 cm.getActiveNetworkInfo().isAvailable();
			NetworkInfo[] info = cm.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		}
		return false;
	}
