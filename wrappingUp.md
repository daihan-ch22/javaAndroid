KEYWORDS

<br>


----


# EVENT HANDLING

* Event - Listner
* Toast 
* SnackBar
* MotionEvent, KeyEvent, onTouchListner, onClickListner, onKeyListner...

<br>




# INFLATION 

foo.xml ------ CONNECT ------ foo_activity.java <br>
: xml파일을 메모리에 객체화(Inflate)해서 뷰로 만들고, 자바 소스에서 사용 가능하게 된다.

  setContentView() / getSystemService(Context.LAYOUT_INFLATER_SERVICE)

----
<br><br>

# ViewBinding
- 뷰를 사용하기 위해서는 액티비티에서 findViewById를 해서 가져와야 하는데 이렇게 되면 코드도 길어지고 귀찮다.
- 따라서 ViewBinding이라는 것을 사용할 수 있음. 자매품으로 DataBinding도 있음. 

```Java
1. build.gradle(Module:app)에 
    
    buildFeatures {
        viewBinding = true
        dataBinding = true
    } 
    요 코드를 추가하고 sync gradle

2. 그러면 '뷰이름'Binding이라는 객체로 뽑아주는데 이걸 OnCreate()에서 
    '뷰이름'Binding.inflate(getLayoutInflater())으로 초기화해 변수에 할당


3. View view = binding(위에서 할당한 변수명).getRoot()로 받음 그리고 setContentView(view); 를 하면 준비 끝

4. 그 이후로는 binding.button2.setOnClickListner(new View.setOnClickListner()) 이렇게 바로 사용 가능 


```




<br><br>

# LISTVIEW & RECYCLER VIEW
    ListView보다는 RecyclerView를 더 많이 사용함.
    다만 Adapter개념은 공유된다


- 리스트뷰/리싸이클러뷰 자체는 '틀' 이고 그 안의 데이터는 Adapter에서 관리한다. 
  - RecyclerView.xml 을 객체에 붙임 
  - 뷰 리스트 하나에 들어갈 내용물 xml만들어서 인플레이션 해놓고 
  - 연동

* Adapter 
  * 이게 메인이 된다. 


-----



<br><br><br>

# 4대 구성요소 + Intent

<br>

## Activity

* AndroidManifest.xml
* Activity switching ----> Activity Stack
* Activity Manager

* Activity LifeCycle

StartActivity / StartActivityResult


[StartActivityResult - Deprecated]
```
setResult(RESULT_CODE , Intent_Object) 해서 넘어온 결과가 아래에 걸림 세트로 생각


ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult() , result ->
            {
                if(result.getResultCode() == RESULT_OK){
                    //LOGICS TO BE WRITTEN HERE
                }
            });
```



<br><br>

## Service

* startService() / onCreate() / onDestroy()
* UI가 없다(안보임)

<br><br>

## Broadcast Receiver
* 전화나 SMS메시지, 채팅 (전화 / 채팅이 왔습니다 == Global Event를 처리하기 위함) 
* UI가 없다(안보임)
* manifest에 등록을 해야 사용 가능 
* start activity/service처럼 따로 시작을 할 필요가 없음 -> manifest에 등록해놓으면 된다. (방법1)
* 
* Programmatic하게 하려면 IntentFilter에 Actions을 등록(방법2), 그걸 RegisterReceiver을 통해 리시버에 등록
  * Listner를 만들어서 리시버를 통해 액션을 받고 그걸 수행하도록 구현하는게 큰 그림 
* 커스텀으로도 만들어서 사용 가능 (다운완료시, 특정시간시, 게임 이벤트 시간 등등)
* Activity onDestroy에 꼭 unRegisterReceiver를 해줘야 메모리 누수 방지 

```xml
<receiver
            android:name=".SMSReceiver"
            android:enabled="true"
            android:exported="true">
            
            <intent-filter> //인텐트 이것만 사용하겠다 
                <action android:name="android.provider.Telephony.SMS_RECEIVED"/>
            </intent-filter>
            
        </receiver>
```
```java 정형화된 문자 관련 리시버 코드

private SmsMessage[] parseSmsMessage(Bundle bundle){
        Object[] objs = (Object[])bundle.get("pdus");
        SmsMessage[] messages = new SmsMessage[objs.length];

        for(int i=0;i<objs.length;i++){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                String format = bundle.getString("format");
                messages[i] = SmsMessage.createFromPdu((byte[])objs[i], format);
            }
            else{
                messages[i] = SmsMessage.createFromPdu((byte[])objs[i]);
            }

        }
        return messages;
    }

```

* Broadcast는 2가지로 나뉨
  * [일반 브로드캐스트] - sendBroadcase()로 호출
    * 비동기 실행 / 모든 수신자는 순서 없이 실행(때로는 동시에)
    * 효율적이지만 한 수신자의 처리 결과를 다른 수신자가 이용할 수 없고 중도 취소 불가

  * [순차 브로드캐스트] - sendOrderedBroadcase()로 호출
    * 한번에 하나의 수신자에만 전달 - 순서대로 진행된다.
    * 중간에 취소하면 다음 수신자는 받지 못한다. 
    * 순서는 Intent Filter 속성으로 정할 수 있고 순서가 같으면 랜덤 실행 


<br><br>

## Content Provider

<br><br>

## Intent

putExtra() / 

[객체전달용] Parcelable / Serializable *Parcelable이 훨씬 빠른데 전부 구현해야함 

비슷한것으로 Bundle클래스가 있는데 Intent: 데이터를 전달하기 위한 목적(어차피 내부적으로 Bundle에 담아서 보냄) / Bundle: 데이터를 저장하고 활용할 목적(상태저장/복구 등) (Map<>형식)



<br><br>

## Fragment
--> 하나의 전체화면 위에 보이는 부분화면을 만들때 사용


- Activity처럼 xml과 Java파일이 한 쌍으로 연결 
- java에서는 Fragment를 상속받고 onCreateView를 오버라이딩해서 재정의한다. 
- 이 안에서 xml 만든것을 Inflation해서 리턴 시키면 콜백 함수가 된다. 
- 인플레이션을 수동으로 해줘야함 (콜백 함수 - setContent없다. 액티비티가 아님
- fragment는 실제 뷰는 아니고 부분화면을 담기위한 틀이라고 보면 됨
- 액티비티가 아니다보니 가볍고 보안상 이점이 있는듯 
- fragment끼리 서로 접근하면 안된다. (액티비티에서 메서드를 만들어 그걸로 왓다갔다)
  )

fragment를 추가하는데는 2가지 방법이 있음.
    1. xml - java Inflation
    2. Java - new 연산자로 새로운 객체를 만들어 추가

    방법 1
    인플레이션을 수동으로 해줘야함 (콜백 함수 - setContent없다. 액티비티가 아니라서)
    fragment는 실제 뷰는 아니고 부분화면을 담기위한 틀이라고 보면 됨

    방법 2
    new로 객체를 만든 다음에 
    FragmentManager가 관리하도록 한다. 
    매니저 부르고 -> transaction시작 -> 프래그먼트 컨테이너(id지정한영역)에 프래그먼트를 올리고 -> 시작(commit)

    ```java
    MainFragment fragment1 = new MainFragment();
                getSupportFragmentManager().beginTransaction().add(R.id.container, fragment1).commit();
    ```

- fragment 에서 activity로 데이터를 보내거나 할때 인터페이스가 적극적으로 쓰임 (프래그먼트에 있는 버튼을 누르면 액티비티의 버튼내용이 바꾸는것 처럼 )
- activity에서는 fragment객체를 바로 참조할 수 있음. 
- 데이터를 받아오는 작업은 왠만하면 activity쪽에서 하는게 좋다. 

<br><br>

### 안드로이드 메뉴는  옵션메뉴와 Context메뉴로 나눌 수 있다.

- res폴더에 menu directory만들고 거기에 xml파일로 메뉴내용물 정의 가능 
- 그리고 그걸 MainActivity에서 onCreateOptionMenu를 오버라이딩해서 인플레이션 한다. 

```java
@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
```
<br>


# ViewPager2
- 프래그먼트를 스크롤하면서 보는 화면 (각각의 프래그먼트에 뷰를 설정해놓는다.)
- ViewPager2가 ViewPager에 비해 향상됨. 다만 RecyclerView처럼 adapter를 만들어서 거기서 실제 데이터랑 붙이는 식으로 사용함
- PageTitleStrip (현재 어디 위치에 있는지 그림으로 보여주는것) - ViewPager2에서는 아직 지원을 하지 않아서 TabLayout을 우회해서 사용함. (네이버웹툰 월~금 목록 같이)

[ViewPager2]
Adapter -> extends FragmentStateAdapter
Fragment -> extends Fragment (java file + xml file)

```java
        //TabLayoutMediator전에 반드시 ViewPager2에 adapter를 붙여줘야한다.
        //.attach는 ViewPager2가 Adapter에 붙은 후에 사용가능하기 떄문 
        new TabLayoutMediator(tabLayout, pager,
                (tab, position) -> tab.setText("Tab " + (position+1))).attach();
```
<br><br>

---

# **THREAD & HANDLER**
* Thread
* MessageQueue
* Handler

Thread에서 UI객체에 동시에 접근하면 데드락 걸림. 

자바의 Thread와 구현 방식이 약간 다르다. 

```
네트워크 관련 기능 사용시에는 반드시 Thread를 사용해야 한다. 
그래서 네트워크로 UI업데이트를 하려면 Handler를 사용해야한다.
Main Thread에서만 UI 구성 요소에 접근이 가능하기 때문에 MessageQueue에 접근하려는 코드를 넣고 Handler가 순차적으로 요청을 보내서 실행  
```

<br>

--> 귀찮다. <br><br>

AsyncTask로 쓸 수 있었는데 Deprecated되서 RxJava같은걸로 사용해야함.

Volley나 retrofit2이 인터넷 활동시 이걸 편하게 해주는걸로 알고있는데 이부분은 확인 필요. (쓰레드랑 연관이 어떻게 있는지 )

* View를 수정하는 것은 MainThread에서만 가능하고 그냥 subThread에서 수정하면 예외발생함 (중요)
  * 이럴때 쓰는 방법이 runOnUiThread(new Runnable) 구현해서 이 내부의 run()안에서 뷰를 수정해야한다. 



<br><br>

# **VOLLEY**
* RequestQueue(한번만 만들면 됨)
* requestQueue에 request객체를 넣어줌 (알아서 쓰레드/핸들러 작업)
* ResponseListner(Request객체 만들때 안에 있음) <br><br>

- 보통 App안에서 구성을 하는것이 통상적

```java
/** Volley의 RequestQueue 만들어놓기 
*   RequestQueue는 앱 시작때 초기화만하고 계속 사용할 수 있음. 매번 만들필요 X
*   따라서 AppHelper같은 클래스를 만들어서 그 안에 static으로 정의해 글로벌로 사용 가능 
*   RequestQueue requestQueue =  Volley.newRequestQueue(getApplicationContext());
**/
//StringRequest 객체에서 어떤 요청을 보낼껀지 정의를 하고나서 
//해당 객체를 RequestQueue객체에 add한다
StringRequest request = new StringRequest(Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        println("response : " + response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        println("error : " + error.getMessage());
                    }
                })
        //위에서 StringRequest로 요청할때 POST처럼 특정 파라미터를 같이 전달하고 싶다면 아래처럼 { } 안에 Request클래스의 getParams()를 오버라이딩
        {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                return params;
            }
        };
        request.setShouldCache(false); //캐싱 false -> 이전 결과가 있더라도 새로 Request객체 만들기

        AppHelper.requestQueue.add(request); // 보통 요청을 보냈다는 메시지는 여기서 출력해준다.
        println("REQUEST SENT!");
```
<br><br>

# Notification

* API 26 (Oreo) 이상부터 무조건 최소 하나의 채널에 등록이 되야함 
  
### 키워드 
```Java
- NotificationManager
- NotificationChannel
- NotificationCompat.Builder (여기다가 설정 등록)
- Intent
- PendingIntent
    - 알림이 떴을때 그걸 누르면 특정 동작을 해야할때 사용. 
    - PendingIntent로 등록해놓으면 다른 앱으로 넘어가있더라도 다른앱에서의 Intent로써 작용하도록 사전에 확정해놓는 개념인거 같음.
```


<br><br>

