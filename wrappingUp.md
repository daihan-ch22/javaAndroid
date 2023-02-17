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

### Service

* startService() / onCreate() / onDestroy()
* UI가 없다(안보임)

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

### Content Provider

### Intent
putExtra() / Parcelable / Serializable 