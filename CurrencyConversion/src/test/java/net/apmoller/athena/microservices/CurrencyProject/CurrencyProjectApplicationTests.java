//package net.apmoller.athena.microservices.CurrencyProject;
//import net.apmoller.athena.microservices.CurrencyProject.controller.CurrencyConversionController;
//import net.apmoller.athena.microservices.CurrencyProject.dto.CurrencyConversionDto;
//import net.apmoller.athena.microservices.CurrencyProject.services.CurrencyConversionService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.Import;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.reactive.server.WebTestClient;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//import reactor.test.StepVerifier;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(SpringExtension.class)
//@WebFluxTest(controllers = CurrencyConversionController.class)
//@Import(CurrencyConversionService.class)
//class CurrencyProjectApplicationTests {
//		@Autowired
//		private WebTestClient webTestClient;
//		@MockBean
//		private CurrencyConversionService service;
//
//		@Test
//		public void addProductTest() {
//			Mono<CurrencyConversionDto> productDtoMono = Mono.just(new CurrencyConversionDto( "USD", "ads", 1, true, "JKR",null,null,null, "JKR",null));
//			when(service.saveCurrencyData(productDtoMono)).thenReturn(productDtoMono);
//
//			webTestClient.post().uri("/currencyconversion")
//					.body(Mono.just(productDtoMono), CurrencyConversionDto.class)
//					.exchange()
//					.expectStatus().isOk();//200
//		}
//
//	@Test
//	public void getProductsTest(){
//		Flux<CurrencyConversionDto> productDtoFlux=Flux.just(new CurrencyConversionDto("USD", "ads", 1, true, "JKR",null,null,null, "JKR",null));
//
//		when(service.getAllCurrencyDatas()).thenReturn(productDtoFlux);
//
//		Flux<CurrencyConversionDto> responseBody = webTestClient.get().uri("/currencyconversion")
//				.exchange()
//				.expectStatus().isOk()
//				.returnResult(CurrencyConversionDto.class)
//				.getResponseBody();
//
//		StepVerifier.create(responseBody)
//				.expectSubscription()
//				.expectNext(new CurrencyConversionDto( "USD", "ads", 1, true, "JKR",null,null,null, "JKR",null))
//
//				.verifyComplete();
//
//	}
//	@Test
//	public void getProductTest(){
//		Mono<CurrencyConversionDto> productDtoMono=Mono.just(new CurrencyConversionDto( "USD", "ads", 1, true, "JKR",null,null,null, "JKR",null));
//		when(service.getCurrencyDataByCode(any())).thenReturn(productDtoMono);
//
//		Flux<CurrencyConversionDto> responseBody = webTestClient.get().uri("/currencyconversion/1")
//				.exchange()
//				.expectStatus().isOk()
//				.returnResult(CurrencyConversionDto.class)
//				.getResponseBody();
//
//		StepVerifier.create(responseBody)
//				.expectSubscription()
//				.expectNextMatches(p->p.getCreated_by().equals("JKR"))
//				.verifyComplete();
//	}
//	}
