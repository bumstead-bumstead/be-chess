# be-chess
소프티어 부트캠프 2기 체스 프로젝트

---

## step 2 todo (or 기록 사항)
- white, black이 아닌 입력에 대한 예외처리, 추가적인 케이스 구현 필요
- 4번 요구 사항은 추가적인 작업이 필요하지 않았다. (Pawn.add() 메서드가 Pawn 객체만을 입력받기 때문에)
- 중복 찾아내는 어려움 + 메서드 분리 정도에 대한 고민 -> assert 메서드를 다시 wrapping할 필요가 있을 지? 가독성과 코드 길이의 trade off <br> -> 래핑한 메서드가 재사용되지 않을 것 같고, 충분히 가독성이 있다고 판단해서 분리하지 않음
-> BoardTest, PawnTest 리팩토링 필요 parameterized test, 다른 코드 참고해서. 

## step 3 기록 사항   

- 유지보수하기 좋은 코드란? 체스 애플리케이션에서 요구사항이 어떻게 바뀔 수 있는 지. 이 부분을 어떻게 cover할 수 있을 지 고민
- 위치에 대한 정보를 Pawn 객체에서 저장할 지, board에서 저장할 지에 대한 고민 -> 두 클래스의 의미, 본질을 생각했을 때 후자라고 생각
- 기물의 representation을 어떻게 구현할 것인가? -> 생성자에서 인자를 따로 받는 것은 비효율적이라고 판단했다. representation은 color에 종속되는 요소이기 때문에, 의미가 중복되고 정합성을 해칠 수 있을 것이라고 생각했다.
- 요구 사항이 단계별로 잘게 나뉘어져 있어서 이것에 의존하게 되는 것 같다. 기능 요구사항만 보고 어떤 형태로 코딩해야할 지 고민하는 과정을 먼저 가지는 식의 학습 방식도 좋을 것 같다는 생각이 들었다.
- Pawn의 생성자에서 입력값을 uppercase로 바꾸는 부분 역할 분리 필요할 지 고민 <br>
- BoardTest에서 initialize() 테스트하는 함수는, 초기화된 board list를 직접 비교하는 것이 엄밀한 것 아닌가?
- 입출력을 main()에서 모두 구현하는 게 맞을 지에 대한 고민
- enum으로 (color, representation)을 나타내기?
-> color에 완전히 종속되는 representation을 입력하는 과정을 생략할 수 있다.
- @DisplayName으로 각 테스트 메서드의 기능 명시하기