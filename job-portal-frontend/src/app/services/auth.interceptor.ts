import { HttpInterceptorFn } from "@angular/common/http";
import { environment } from "../../environments/environment";
import { getCookie } from "../utils/cookies-utils";


export const authInterceptor: HttpInterceptorFn = (req, next) => {
    const token = getCookie(environment.token);

    if(token){
        const authReq = req.clone({
            headers: req.headers.set('Authorization', `Bearer ${token}`)
        });
        return next(authReq);
    }

    return next(req);
}