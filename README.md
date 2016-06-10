# ZJHRetrofit
Retrofit+RxJava网络异步集成

使用方法：
ContributorRequest request = new ContributorRequest(this);
request.asyncExecute(new ContributorRequestCallback(this));

class ContributorRequestCallback extends HttpCallback<List<Contributor>>{

        public ContributorRequestCallback(Context context) {
            super(context);
        }

        @Override
        public void onSuccess(List<Contributor> result) {
            for (Contributor contributor : result) {
                System.out.println(contributor.login + " (" + contributor.contributions + ")");
            }
        }
    }
