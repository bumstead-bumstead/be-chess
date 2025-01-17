# be-chess
소프티어 부트캠프 2기 체스 프로젝트

---
  
  ## step 2 기록 사항
- 중복 찾아내는 어려움 + 메서드 분리 정도에 대한 고민 -> assert 메서드를 다시 wrapping할 필요가 있을 지? 가독성과 코드 길이의 trade off <br> -> 래핑한 메서드가 재사용되지 않을 것 같고, 충분히 가독성이 있다고 판단해서 분리하지 않음

## step 3 기록 사항
- 유지보수하기 좋은 코드란? 체스 애플리케이션에서 요구사항이 어떻게 바뀔 수 있는 지. 이 부분을 어떻게 cover할 수 있을 지 고민
- 위치에 대한 정보를 Pawn 객체에서 저장할 지, board에서 저장할 지에 대한 고민 -> 두 클래스의 의미, 본질을 생각했을 때 후자라고 생각
- 기물의 representation을 어떻게 구현할 것인가? -> 생성자에서 인자를 따로 받는 것은 비효율적이라고 판단했다. representation은 color에 종속되는 요소이기 때문에, 의미가 중복되고 정합성을 해칠 수 있을 것이라고 생각했다.
- 요구 사항이 단계별로 잘게 나뉘어져 있어서 이것에 의존하게 되는 것 같다. 기능 요구사항만 보고 어떤 형태로 코딩해야할 지 고민하는 과정을 먼저 가지는 식의 학습 방식도 좋을 것 같다는 생각이 들었다.
- Pawn의 생성자에서 입력값을 uppercase로 바꾸는 부분 역할 분리 필요할 지 고민 <br>
- BoardTest에서 initialize() 테스트하는 함수는, 초기화된 board list를 직접 비교하는 것이 엄밀한 것 아닌가?
- 입출력을 main()에서 모두 구현하는 게 맞을 지에 대한 고민
- enum으로 (color, representation)을 나타내기

## step 4 기록 사항
- private 생성자를 만들어 직접 객체 생성을 막는 방법의 장점 고민
- Piece의 representation이 더 이상 color에 종속되지 않는다. 어떻게 관리하는 것이 좋을 지.. 따로 enum으로 빼는 게 맞을 지...
- enum을 문자열로 변환해서 비교하는 게 아니라, enum 객체 자체를 이용하기

## step 5 기록 사항
- 행을 Rank로 wrapping해서 관리하는 것의 장점에 대한 고민. Board에서 해당 List의 역할을 정확하게 알 수 있고, Piece 자체에 대한 의존을 줄인다. 은닉, 또한 해당 List에 접근하는 메소드들에도 추가적인 로직을 담을 수 있다. 이게 일급 클래스군
  -> 행마다 발생하는 추가적인 기능을 구현하기에 편리해졌다. Rank라는 의미를 부여할 수 있다. 명세에서 Rank 별로 처리해야하는 로직 등이 있다면 확실히 이와 같은 리팩토링이 필요할 것 같다.
- representation을 Type enum의 필드로 추가하는 것이 바람직한가. 확실한 장점은 representation에 대한 로직을 확실하게 감추고 간결하게 포함할 수 있다는 점.
- enum을 Piece 내부 클래스로 선언함으로써 의미를 명확하게, 계층 구조를 깔끔하게 할 수 있다.
- 팩토리 메서드 패턴을 적용해서 내부를 더 잘 숨길 수 있게 되었다. 
- Board 상의 빈 공간도 Piece 객체로 채워넣는 것의 장점은, 보드 2차원 배열 상의 원소들을 일관성 있게 관리할 수 있다는 점에서 발생한다고 생각한다.
  - null로 인해 발생하는 예외에서 자유로워진다.
  - Board의 빈 공간이라는 의미를 부여할 수 있다.
  - 출력과 기물 관리, 이동 등의 로직을 일반화할 수 있다. 예를 들어, 이전의 코드에서는 빈 공간을 출력하는 부분에서 null check 후 추가적인 상수 문자를 출력해야했다.
- inline variable이 좋을 지, 변수를 선언해서 반환하는 게 좋을 지에 대해서 고민했다. 해당 부분이 얼마나 직관적으로 읽히는 지에 따라서 결정할 수 있을 것 같다.
- Position을 클래스로 분리하는 것의 장점
- Piece comparable 구현에 대한 고민 -> 명세에 따라 다르겠지만, Piece 객체 자체를 비교하는 기준이 score는 아닐 것이라고 판단해서, Piece가 comparable을 구현하지 않고 board의 메서드단에서 comparator를 구현했다.

## step 6 기록 사항
- Position 정보를 Piece에서도 유지하니까 정보가 중복된다. 같은 정보를 동시에 두 곳에 유지해야한다. 여기서 발생하는 추가적인 비용 + 오류 발생 가능성이 있다. 
- 새로운 요구사항이 발생했을 때, 이를 추가하기 위한 수정을 많이 했다. -> 확장성을 고려하면서 코드를 짜는 연습을 하면 좋을 것 같다.
- ChessGame이 Board 내 필드인 pieces와 Piece, Rank 객체에까지 의존한다. 의존 관계가 너무 복잡하다고 생각해서, Rank 내의 객체들을 더 숨기는 방식으로 리팩토링했다.
- 이동하려는 위치에 같은 편 기물이 있는지 확인하는 로직은 기능의 특성 상 Board의 일이라고 판단해서 구현하지 않았다.
- 기물 생성 팩토리 메서드를 각 자식클래스마다 만드는 것이 괜찮은가.. 중복이 늘어난다.
- 기물 객체에 VO, 팩토리 메소드를 적용하는 데는 장단점이 있는 것 같다. 이동과 다양한 이동 로직 때문에 상태가 변하는 경우가 많아서, 이 과정에서 추가적인 작업이 필요하다는 점이 단점이다.
- 일반화, 즉 인터페이스 부분을 일반적으로 만드는, 객체를 wrapping하는 작업의 중요성에 대해서 깨달았다. 과제 명세를 따라가느라 팩토리 메소드를 색/타입마다 모두 선언했다. 이 상황에서 명세가 변경되었을 때, 리팩토링해야하는 코드의 양이 매우 많았다.
Piece의 팩토리 메소드가 일반적으로 작성되어있었다면, (하나의 팩토리 메소드만이 외부에 대한 인터페이스였다면) 추상 메소드로 변경하기만 하면 되기 때문에 수정이 적었을 것이라고 생각했다. 팩토리 클래스를 따로 만드는 것. 수정에는 닫혀있고, 확장에는 열려있는 코드가 된다.
확장성에 대한 고민 

## 그 후 리팩토링 고민 사항 기록
- 예외 처리를 어디에서 할 것인가? 지금 main의 move가 맡은 로직을 ChessGame으로 옮기고, main은 이것에 의존하고 던져진 예외에 따라 처리하는 역할을?
  -> ChessApp에서 하는 것이 맞는 것 같다. Controller와 비슷한 역할. 전반적인 게임을 진행시키는 역할을 App이 맡는 것. main은 단지 entry point일 뿐!! 
  -> ChessGame에서 처리하면 App단이 예외를 확인할 수 없고, main에서 처리하면 애플리케이션이 모듈화되지 않는다.
  -> 그렇다면 Main은 App을 호출하는 일만을 해야겠다. 
- turn을 나타내는 변수를 Piece.Color로 해도 괜찮을까? 의미 상으로도 적절하고 코드도 간결해질 것 같긴 한데, Main이 Piece에 의존하는 것 같다. 라고 하기엔 단순히 내부 enum이라서 의존관계 때문에 문제가 생길 것 같지 않다.
- Piece를 연속적인 이동을 하는 기물과 한 칸만 이동하는 기물로 구분해서 상속하게 하는 것의 장점은? 
  - 메소드 일반화 가능, 따라서 중복 제거 가능
  - board, rank에서 이동 관련 로직 깔끔하게 검증하기 가능
- position string to Position -> App 단에서 모두 처리해서 주는게 맞는 것 같다.
  -> 입력된 string 값에 대해서는 App만이 알고 있고, 그 아래는 모두 Position 객체로만 교류한다. -> 무분별하게 ChessPositionParser에 의존하고, 중복 코드가 발생하는 문제가 해결된다.
- 웹으로 구현되는 경우를 생각해서 view를 인터페이스로 뽑아내는 방안에 대해서 고민했다. 템플릿에 넘겨주는 view는 그 형태나 return type도 많이 다를 것 같은데..
- Piece의 Position 필드를 없애는 과정에서, 정적 팩토리 메소드의 단점을 파악했다. 팩토리 클래스를 따로 선언해서 기물 별로 이를 구현하게 했다면 수정 사항이 훨씬 줄어들었을 것이다.
  또한 상속 관계를 더 추가해서 (ex : 퀸, 비숍, 룩과 같은 이동 로직을 갖는 기물과 그렇지 않은 기물) 이동 로직을 추상화했다면 중복을 줄이고 수정도 줄일 수 있었을 것이다. -> 실제로 그랬다.
- 테스트 코드 중에서도 중복이 매우 많다. 상속이나 일반화가 가능할까? (기물 관련 로직들이 겹친다.)
- VO로 많이 생성해서 메모리 낭비가 많을 것 같다. gc된다고 해도 여기서 성능 저하가 발생한다. -> move 로직 수정 필요할 것 같다. -> 완료

### 230712
- 기물 팩토리 클래스 -> PieceFactory를 인터페이스로 만들고 기물 별로 이것을 구현하게 하는 방식의 장점은? 확장성 밖에 없지 않나? 체스에서는 필요하지 않는 것 같다. 단순히 static 팩토리 메소드를 포함하는 하나의 클래스를 선언하는 식으로 구현
- Piece, Rank, Board 간의 의존 관계. Board에게 Rank 아랫 단계를 얼마나 감출 수 있고, 얼마나 감추는 게 좋을까? -> Piece에 아예 의존하지 않을 순 없다. 당연히 정답이 있는 문제가 아니고, Rank 단위로 할 수 있는 작업은 의미 부여/wrapping하는 것이 좋을 것 같다.
- turn 변수는 game에서 유지하는게 맞지 않나?? 아닌 것 같기도 -> 옮겨보자. verify 로직을 모두 ChessGame으로 옮기는 방안도 생각해보기
- pawn에만 해당하는 검증 로직이 많이 생긴다. if문으로 따로 분기하는 등. 하지만 Piece가 다른 객체들에 의존함으로써 생기는 복잡하고 강한 결합 관계가 더 큰 부작용일 것이라고 생각했다. 충분히 읽기 쉽고, 코드의 흐름이 일관성 있다.

### 230713
- verify 로직들을 모두 ChessGame에서 구현하는 방안을 생각해봤지만, 메소드 인자도 매우 많아서 의미를 파악하기 힘들고, 의존 관계가 더 복잡해졌다. Board 등에 더 많이 의존하게 되는 문제가 발생 -> 역할을 더 생각해서 분리, wrapping
- 모든 설계에서 코드의 가독성, 의존 관계의 복잡성과 일관성, 확장성을 고려하면서 작성해서 많은 연습이 되었다. -> develop
- 리팩토링이 매우 많다. -> 이렇게도 했다가 저렇게도 했다가 하는 작업을 많이 했기 때문이다. 지금이 실력있는 다른 분들과 연습하면서 이것 저것 시도해보고 몸소 깨달으면서 성장할 수 있는 시간이라고 생각해서 많은 시도를 해봤고, 실제로 느끼는 점도 많았다.
  지금은 당장 빠르고 완벽한 결과물을 만드는 시간이 아니라 내가 납득할 수 있는 노하우와 지식을 쌓는 시간이라고 생각한다.
- 요구사항을 보고 만들다보면 거기에 집중하게 되는데, 하루를 시작할 때랑 끝날 때만이라도 한발짝 물러서서 내가 무엇을 하고 있고 어떤 문제가 있고 그래서 어떻게 방향을 바꿔야 할지, 다른 것을 시도해볼지 돌아보는 시간이 있으면 오히려 해야할 일에 더 집중할 수 있습니다.
- 맹목적인 것은 좋지 않다.

## 아직도 가지고 있는 고민 사항
- Board 객체를 Game에서 관리하고 App에게 최대한 감추기 위해서 Game 객체 생성 시점에 (기본 생성자에서) Board 인스턴스를 만들게 했다. 그런데 어차피 App 단에서 Board를 가져오는 로직이 없을 수가 없었다. (View에 넘겨주는 부분) 
  - 결합도를 많이 낮춘 것이라고 생각은 하지만 더 좋은 방법이 있을 지 궁금하다.
  - view를 ChessGame에서 의존하는 방법 : board를 감출 수는 있지만, 입력을 위해서 어차피 App에서 View에 의존하긴 해야한다. trade-off라고 생각한다.