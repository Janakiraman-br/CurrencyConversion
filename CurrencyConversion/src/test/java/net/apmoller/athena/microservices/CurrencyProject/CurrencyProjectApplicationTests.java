package net.apmoller.athena.microservices.CurrencyProject;
import net.apmoller.athena.microservices.CurrencyProject.controller.CurrencyConversionController;
import net.apmoller.athena.microservices.CurrencyProject.dto.CurrencyConversionDto;
import net.apmoller.athena.microservices.CurrencyProject.exception.CurrencyConversionException;
import net.apmoller.athena.microservices.CurrencyProject.services.CurrencyConversionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = CurrencyConversionController.class)
@Import(CurrencyConversionService.class)
class CurrencyProjectApplicationTests {
		@Autowired
		private WebTestClient webTestClient;
		@MockBean
		private CurrencyConversionService service;

		@Test
		public void addProductTest() {
			Mono<CurrencyConversionDto> productDtoMono = Mono.just(new CurrencyConversionDto( "USD", "ads", 1, true, "JKR","12-08-2021",true));
			when(service.saveCurrencyData(productDtoMono)).thenReturn(productDtoMono);

			webTestClient.post().uri("/currencyconversion")
					.body(Mono.just(productDtoMono), CurrencyConversionDto.class)
					.exchange()
					.expectStatus().isOk();//200
		}

	@Test
	public void getProductsTest(){
		Flux<CurrencyConversionDto> productDtoFlux=Flux.just(new CurrencyConversionDto("USD", "ads", 1, true, "JKR","12-08-2021",true));

		when(service.getAllCurrencyDatas()).thenReturn(productDtoFlux);

		Flux<CurrencyConversionDto> responseBody = webTestClient.get().uri("/currencyconversion")
				.exchange()
				.expectStatus().isOk()
				.returnResult(CurrencyConversionDto.class)
				.getResponseBody();

		StepVerifier.create(responseBody)
				.expectSubscription()
				.expectNext(new CurrencyConversionDto( "USD", "ads", 1, true, "JKR","12-08-2021",true))

				.verifyComplete();

	}
	@Test
	public void getProductTest()  {
		Mono<CurrencyConversionDto> productDtoMono=Mono.just(new CurrencyConversionDto( "USD", "ads", 1, true, "JKR","12-08-2021",true));
		when(service.getCurrencyDataByCode(any())).thenReturn(productDtoMono);

		Flux<CurrencyConversionDto> responseBody = webTestClient.get().uri("/currencyconversion/conversionkey/USD")
				.exchange()
				.expectStatus().isOk()
				.returnResult(CurrencyConversionDto.class)
				.getResponseBody();

		StepVerifier.create(responseBody)
				.expectSubscription()
				.expectNextMatches(p->p.getConversionKey().equals("USD"))
				.verifyComplete();
	}
	@Test
	public void getNameTest()  {
		Mono<CurrencyConversionDto> productDtoMono=Mono.just(new CurrencyConversionDto( "USD", "ads", 1, true, "JKR","12-08-2021",true));
		when(service.getCurrencyDataByCode(any())).thenReturn(productDtoMono);

		Flux<CurrencyConversionDto> responseBody = webTestClient.get().uri("/currencyconversion/conversionkey/USD")
				.exchange()
				.expectStatus().isOk()
				.returnResult(CurrencyConversionDto.class)
				.getResponseBody();

		StepVerifier.create(responseBody)
				.expectSubscription()
				.expectNextMatches(p->p.getConversionName().equals("ads"))
				.verifyComplete();
	}
	@Test
	public void getStatusTest()  {
		Mono<CurrencyConversionDto> productDtoMono=Mono.just(new CurrencyConversionDto( "USD", "ads", 1, true, "JKR","12-08-2021",true));
		when(service.getCurrencyDataByCode(any())).thenReturn(productDtoMono);

		Flux<CurrencyConversionDto> responseBody = webTestClient.get().uri("/currencyconversion/conversionkey/USD")
				.exchange()
				.expectStatus().isOk()
				.returnResult(CurrencyConversionDto.class)
				.getResponseBody();

		StepVerifier.create(responseBody)
				.expectSubscription()
				.expectNextMatches(p->p.isStatus())
				.verifyComplete();
	}
	@Test
	public void getCreatedByTest()  {
		Mono<CurrencyConversionDto> productDtoMono=Mono.just(new CurrencyConversionDto( "USD", "ads", 1, true, "JKR","12-08-2021",true));
		when(service.getCurrencyDataByCode(any())).thenReturn(productDtoMono);

		Flux<CurrencyConversionDto> responseBody = webTestClient.get().uri("/currencyconversion/conversionkey/USD")
				.exchange()
				.expectStatus().isOk()
				.returnResult(CurrencyConversionDto.class)
				.getResponseBody();

		StepVerifier.create(responseBody)
				.expectSubscription()
				.expectNextMatches(p->p.getCreatedBy().equals("JKR"))
				.verifyComplete();
	}

	@Test
    public void dynamicTest(){
        Flux<CurrencyConversionDto> productDtoFlux=Flux.just(new CurrencyConversionDto("USD", "ads", 1, true, "JKR","12-08-2021",true));

        //when(service.getByProp("USD","ads",6,true,null,null)).thenReturn(productDtoFlux);

        Flux<CurrencyConversionDto> responseBody = webTestClient.get().uri("/currencyconversion/customrepo?conversionName=ads")
                .exchange()
                .expectStatus().isOk()
                .returnResult(CurrencyConversionDto.class)
                .getResponseBody();

        StepVerifier.create(responseBody)
                .expectSubscription()
                //.expectNext(new CurrencyConversionDto( "USD", "ads", 1, true, "JKR","12-08-2021",true))
                .expectNextMatches(p->p.getConversionName().equals("ads"))
                .verifyComplete();

    }

//	@Test
//	public void addSavedProductTest() {
//		Mono<CurrencyConversionSavedSearchDto> productDtoMono = Mono.just(new CurrencyConversionSavedSearchDto( "USD", "ads", 1, true, "JKR","12-08-2021",true));
//		when(service.addSearchedCurrencyName(productDtoMono)).thenReturn(productDtoMono);
//
//		webTestClient.post().uri("/currencyconversion/savedsearch")
//				.body(Mono.just(productDtoMono), CurrencyConversionDto.class)
//				.exchange()
//				.expectStatus().isOk();//200
//	}
//	@Test
//	public void getSavedSearchTest(){
//		Flux<CurrencyConversionSavedSearchDto> productDtoFlux=Flux.just(new CurrencyConversionSavedSearchDto("USD", "ads", 1, true, "JKR","12-08-2021",true));
//
//		when(service.getAllSavedSearchDatas()).thenReturn(productDtoFlux);
//
//		Flux<CurrencyConversionSavedSearchDto> responseBody = webTestClient.get().uri("/currencyconversion/savedsearch")
//				.exchange()
//				.expectStatus().isOk()
//				.returnResult(CurrencyConversionSavedSearchDto.class)
//				.getResponseBody();
//
//		StepVerifier.create(responseBody)
//				.expectSubscription()
//				.expectNext(new CurrencyConversionSavedSearchDto( "USD", "ads", 1, true, "JKR","12-08-2021",true))
//
//				.verifyComplete();
//
//	}
	}
