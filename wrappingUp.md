KEYWORDS

<br>

## LAYOUT


<br><br>

----


## EVENT HANDLING

* Event - Listner
* Toast 
* SnackBar
* MotionEvent, KeyEvent, onTouchListner, onClickListner, onKeyListner...



<br><br>

----

## INFLATION 

foo.xml ------ CONNECT ------ foo_activity.java
: xml파일을 메모리에 객체화(Inflate)해서 뷰로 만들고, 자바 소스에서 사용 가능하게 된다.

* setContentView() / getSystemService(Context.LAYOUT_INFLATER_SERVICE)

----

<br><br>

## LISTVIEW
    ListView보다는 RecyclerView를 더 많이 사용함.
    다만 Adapter개념은 공유된다

리스트뷰 자체는 껍데기이고 그 안의 데이터는 Adapter가 관리한다. 

* Adapter


-----



<br><br><br>

# 4대 구성요소 + Intent

<br>

### Activity

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
### Service

* startService() / onCreate() / onDestroy()
* UI가 없다(안보임)

<br><br>

### Broadcast Receiver
* 전화나 SMS메시지, 채팅 (전화 / 채팅이 왔습니다 == Global Event를 처리하기 위함) 
* UI가 없다(안보임)
* manifest에 등록을 해야 사용 가능 
* start activity/service처럼 따로 시작을 할 필요가 없음 -> manifest에 등록해놓으면 된다. 

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

### Content Provider

<br><br>

### Intent

putExtra() / 

[객체전달용] Parcelable / Serializable *Parcelable이 훨씬 빠른데 전부 구현해야함 

비슷한것으로 Bundle클래스가 있는데 Intent: 데이터를 전달하기 위한 목적(어차피 내부적으로 Bundle에 담아서 보냄) / Bundle: 데이터를 저장하고 활용할 목적(상태저장/복구 등) (Map<>형식)



<br><br>

### Fragment
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



### ViewPager2
- 프래그먼트를 스크롤하면서 보는 화면 (각각의 프래그먼트에 뷰를 설정해놓는다.)
- ViewPager2가 ViewPager에 비해 향상됨. 다만 RecyclerView처럼 adapter를 만들어서 거기서 실제 데이터랑 붙이는 식으로 사용함
- PageTitleStrip (현재 어디 위치에 있는지 그림으로 보여주는것) - ViewPager2에서는 아직 지원을 하지 않아서 TabLayout을 우회해서 사용함. 