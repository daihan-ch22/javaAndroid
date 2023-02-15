# KEYWORDS




<br>

## LAYOUT


<br><br>


## EVENT HANDLING

* Event - Listner
* Toast 
* SnackBar
* MotionEvent, KeyEvent, onTouchListner, onClickListner, onKeyListner...



<br><br>


## INFLATION 

foo.xml ------ CONNECT ------ foo_activity.java
: xml파일을 메모리에 객체화(Inflate)해서 뷰로 만들고, 자바 소스에서 사용 가능하게 된다.

* setContentView() / getSystemService(Context.LAYOUT_INFLATER_SERVICE)


<br><br>

## LISTVIEW
    ListView보다는 RecyclerView를 더 많이 사용함.
    다만 Adapter개념은 공유된다

리스트뷰 자체는 껍데기이고 그 안의 데이터는 Adapter가 관리한다. 

* Adapter






<br><br><br>

## 4대 구성요소 + Intent

<br>

### Activity

* AndroidManifest.xml
* Activity switching ----> Activity Stack
* Activity Manager

* Activity LifeCycle

StartActivity / StartActivityResult

### Service
### Broadcast Receiver
### Content Provider

### Intent
putExtra() / Parcelable / Serializable 