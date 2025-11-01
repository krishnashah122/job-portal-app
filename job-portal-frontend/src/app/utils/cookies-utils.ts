export function getCookie(name: string): string | null {
    const cookies = document.cookie;
    const tempCookies = cookies.split(';');
    for(let cookie of tempCookies){
        const [key, value] = cookie.trim().split('=');
        if(key === name) return value;
    }

    return null;
}