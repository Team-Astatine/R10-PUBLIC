package org.Astatine.r10.Contents;

/**
 * 각 이벤트 등록시 Constructor 에서 field variable 을 init 하기 위함입니다.
 * 구현체 Instance 관리는 {@link GlobalEventHandler}을 참고 해주세요.
 */
public interface EventRegister {
    
    /**
     * Event를 실질적으로 실행시키는 함수 입니다.
     */
    void execute();

    /**
     * field variable 을 initialization 할때 사용합니다.
     */
    void init();
}
