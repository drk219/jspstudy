/**
 * 
 */

const getContextPath = () => {  // /mvc를 사용하는건 위험하기 때문(다른곳에서 쓸때는 변경이 가능하기때문)에 url 경로중 일부를 변수처리한다.
  const host = location.host;  //  localhost:8080
  const url = location.href;   //  http://localhost:8080/mvc/getDate.do
  const begin = url.indexOf(host) + host.length;  // host 
  const end = url.indexOf('/', begin + 1);   // host 이후 첫 /의 위치 찾기
  return url.substring(begin, end);
}


const getDateTime = () => {
  const type = document.getElementById('type');
  if(type.value === 'date'){
    location.href = getContextPath() + '/getDate.do';   //.do로 하나의 서블릿으로 운영(preffix)
  } else if(type.value === 'time'){
    location.href = getContextPath() + '/getTime.do';
  } else if(type.value === 'datetime'){
    location.href = getContextPath() + '/getDateTime.do';
  }
}
 
document.getElementById('btn').addEventListener('click', getDateTime);