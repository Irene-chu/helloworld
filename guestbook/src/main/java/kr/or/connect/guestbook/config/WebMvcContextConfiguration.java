package kr.or.connect.guestbook.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

//DispatcherSerlvet이 실행될 때 읽어들이는 설정 파일 정의
@Configuration
@EnableWebMvc
@ComponentScan(basePackages= {"kr.or.connect.guestbook.controller"})
public class WebMvcContextConfiguration extends WebMvcConfigurerAdapter {
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/META-INF/resources/webjars/").setCachePeriod(31556926);
        registry.addResourceHandler("/css/**").addResourceLocations("/css/").setCachePeriod(31556926);
        registry.addResourceHandler("/img/**").addResourceLocations("/img/").setCachePeriod(31556926);
        registry.addResourceHandler("/js/**").addResourceLocations("/js/").setCachePeriod(31556926);
    }
 
    // default servlet handler를 사용하게 합니다.
	// 매핑 정보가 없는 URL 요청을 스프링의 DefaultServletHttpRequestHandler가 처리하도록 함
	// WAS의 DefaultServlet에게 해당 일을 넘기고 static한 자원을 읽어서 보여주게 함
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
   
    // 특정 URL에 대한 처리를 컨트롤러 클래스를 작성하지 않고 매핑할 수 있도록 함
    @Override
    public void addViewControllers(final ViewControllerRegistry registry) {
    		System.out.println("addViewControllers가 호출됩니다. ");
        registry.addViewController("/").setViewName("index"); // "/" 라는 요청이 들어오면 "index"이라는 이름의 뷰로 보여주도록 함
    }
    
    // view name은 ViewResolver라는 객체를 이용하여 찾음
    @Bean
    public InternalResourceViewResolver getInternalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
}
