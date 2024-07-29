// 테마 토글 함수
function toggleTheme() {
    const body = document.body;
    const toggleCircle = document.querySelector('.toggle-circle');
    body.classList.toggle('dark');
    toggleCircle.classList.toggle('dark');

    // 현재 테마를 localStorage에 저장
    if (body.classList.contains('dark')) {
        localStorage.setItem('theme', 'dark');
    } else {
        localStorage.setItem('theme', 'light');
    }
}

// 페이지 로드 시 저장된 테마를 적용
window.addEventListener('DOMContentLoaded', (event) => {
    const savedTheme = localStorage.getItem('theme');
    if (savedTheme) {
        const body = document.body;
        const toggleCircle = document.querySelector('.toggle-circle');
        if (savedTheme === 'dark') {
            body.classList.add('dark');
            toggleCircle.classList.add('dark');
        } else {
            body.classList.remove('dark');
            toggleCircle.classList.remove('dark');
        }
    }
});

// 사이드바 토글 함수
function toggleSidebar() {
    const sidebar = document.querySelector('.sidebar');
    const content = document.querySelector('.content');
    const openButton = document.querySelector('.open-sidebar-button');

    sidebar.classList.toggle('closed');
    openButton.classList.toggle('hidden');

    if (sidebar.classList.contains('closed')) {
        content.style.marginLeft = '30px';
        content.style.marginRight = '0px';
    } else {
        content.style.marginLeft = '270px';
        content.style.marginRight = '-250px';
    }
}

// 페이지 로드 시 로그인 상태를 체크하여 버튼을 업데이트하는 함수
function checkLoginStatus() {
    const loginButton = document.querySelector('#loginButton');
    const adminButton = document.querySelector('#adminButton');
    const accessToken = localStorage.getItem('accessToken');

    if (accessToken) {
        loginButton.textContent = 'Logout';
        loginButton.onclick = onLogout;
        adminButton.style.display = 'inline-block'; // 로그인 상태일 때 ADMIN 버튼 표시
    } else {
        loginButton.textContent = 'Login';
        loginButton.onclick = () => location.href = '/auth';
        adminButton.style.display = 'none'; // 로그아웃 상태일 때 ADMIN 버튼 숨기기
    }
}

// 로그아웃 성공 시 호출되는 함수
function onLogoutSuccess() {
    const loginButton = document.querySelector('#loginButton');
    const adminButton = document.querySelector('#adminButton');
    loginButton.textContent = 'Login';
    loginButton.onclick = () => location.href = '/auth';
    adminButton.style.display = 'none'; // 로그아웃 후 ADMIN 버튼 숨기기

    Swal.fire({
        toast: true,
        position: 'center',
        icon: 'success',
        title: '로그아웃이 정상적으로 실행되었습니다.',
        showConfirmButton: false,
        timer: 3000,
        timerProgressBar: true,
        didOpen: (toast) => {
            toast.addEventListener('mouseenter', Swal.stopTimer);
            toast.addEventListener('mouseleave', Swal.resumeTimer);
        },
        customClass: {
            title: 'black-text'
        }
    });
}

// 로그아웃 요청을 보내는 함수
async function onLogout() {
    try {
        // 실제 로그아웃 API 호출
        const response = await fetch('/api/auth/logout', {
            method: 'POST',
            headers: {
                'Authorization': `Bearer ${localStorage.getItem('accessToken')}`,
                'Content-Type': 'application/json'
            }
        });

        if (response.ok) {
            localStorage.removeItem('accessToken');
            localStorage.removeItem('refreshToken');
            onLogoutSuccess();
        } else {
            alert('로그아웃 실패');
        }

    } catch (error) {
        alert('로그아웃 중 오류 발생: ' + error.message);
    }
}

// DOMContentLoaded 이벤트 리스너에 로그인 상태 체크 함수 추가
document.addEventListener('DOMContentLoaded', (event) => {
    toggleSidebar();
    checkLoginStatus(); // 로그인 상태를 체크하는 함수 호출
});

function refreshPage() {
    location.reload();
}
