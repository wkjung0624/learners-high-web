package com.mightyotter.learnershigh.global.common.response;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
// null 값은 반환하지 않도록 하는 방법 - @JsonInclude(Include.NON_NULL) : https://stackoverflow.com/questions/64868946/how-to-not-return-null-value-in-responseentity
// final 은 내부적으로 어떻게 동작하며, 메모리 관리는 어떻게 이루어지는가?
public class DefaultResponseBody{
	private final String apiVersion; // 현재 API 의 버전, 예) "apiVersion" : "1.0"
	private final String context; // 클라이언트에서 변경할 부분을 지정해주는 값
	private final Long id;
	private final String method; // 데이터에 대해 수행하거나 수행한 작업을 나타냅니다. JSON 요청의 경우 메서드 속성을 사용하여 데이터에 대해 수행할 작업을 나타낼 수 있습니다.
	private final List<String> param; // RPC 요청에만 필요함, 현재는 제외
	private final Object data; // Data Layer, Page Layer, Link Layer 의 값이 포함되는 부분
}
