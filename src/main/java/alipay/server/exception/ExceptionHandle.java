package alipay.server.exception;


import alipay.server.common.constant.Constant;
import com.william.pojo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

/**
 * 全局异常捕捉处理
 * ExceptionHandle
 *
 * @author HMG
 * 创建日期：2018年7月21日
 * Description:
 */
@ControllerAdvice
public class ExceptionHandle {

	private Logger errorLogger = LoggerFactory.getLogger("errorLog");
    /**
     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     *
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
    }

    /**
     * 把值绑定到Model中，使全局@RequestMapping可以获取到该值
     *
     * @param model
     */
    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("author", "Magical Sam");
    }

    /**
     * 全局异常捕捉处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e) {
        // 捕获手动抛出的异常
        if (e instanceof BaseException) {
            BaseException baseException = (BaseException) e;
            return new Result(baseException.getReturnCode(), baseException.getMessage());
        }
        e.printStackTrace();
        errorLogger.error("errorMessage", e);
        return new Result(Constant.ERROR_CODE, e.getMessage());
    }
}