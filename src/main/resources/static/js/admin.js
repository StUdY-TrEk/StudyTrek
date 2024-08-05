let userApprovalRequests = [
    {
        id: 1,
        userName: "김철수",
        bootcampName: "코드스테이츠",
        track: "프론트엔드",
        generation: "47기",
        startDate: "2023-07-01",
        endDate: "2023-12-31",
        requestDate: "2023-06-15",
        certificate: "certificate1.jpg",
        status: "pending"
    },
    {
        id: 2,
        userName: "이영희",
        bootcampName: "패스트캠퍼스",
        track: "백엔드",
        generation: "23기",
        startDate: "2023-08-01",
        endDate: "2024-01-31",
        requestDate: "2023-06-20",
        certificate: "certificate2.jpg",
        status: "pending"
    },
    {
        id: 3,
        userName: "박지훈",
        bootcampName: "멋쟁이사자처럼",
        track: "풀스택",
        generation: "12기",
        startDate: "2023-09-01",
        endDate: "2024-02-29",
        requestDate: "2023-06-25",
        certificate: "certificate3.jpg",
        status: "approved"
    },
    {
        id: 4,
        userName: "최수진",
        bootcampName: "네이버 부스트캠프",
        track: "AI",
        generation: "5기",
        startDate: "2023-10-01",
        endDate: "2024-03-31",
        requestDate: "2023-06-30",
        certificate: "certificate4.jpg",
        status: "rejected"
    }
];

let bootcampApprovalRequests = [
    {
        id: 1,
        managerName: "박진우",
        bootcampName: "테크노캠프",
        requestDate: "2023-07-01",
        businessCertificate: "business_cert1.jpg",
        status: "pending"
    },
    {
        id: 2,
        managerName: "김미란",
        bootcampName: "코딩마스터",
        requestDate: "2023-07-05",
        businessCertificate: "business_cert2.jpg",
        status: "approved"
    },
    {
        id: 3,
        managerName: "이상호",
        bootcampName: "디지털아카데미",
        requestDate: "2023-07-10",
        businessCertificate: "business_cert3.jpg",
        status: "pending"
    }
];

let bootcamps = [
    { managerName: "박진우", bootcampName: "테크노캠프" },
    { managerName: "김미란", bootcampName: "코딩마스터" },
    { managerName: "이상호", bootcampName: "디지털아카데미" }
];

let currentRequestType = 'user';

function displayRequests(category = 'all', sortOrder = 'newest') {
    const requestsList = document.getElementById('requestsList');
    requestsList.innerHTML = '';

    let requests = currentRequestType === 'user' ? userApprovalRequests : bootcampApprovalRequests;
    let filteredRequests = requests;

    if (category !== 'all') {
        filteredRequests = requests.filter(req => req.status === category);
    }

    if (sortOrder === 'newest') {
        filteredRequests.sort((a, b) => new Date(b.requestDate) - new Date(a.requestDate));
    } else {
        filteredRequests.sort((a, b) => new Date(a.requestDate) - new Date(b.requestDate));
    }

    filteredRequests.forEach(request => {
        const requestElement = document.createElement('div');
        requestElement.className = 'request';
        if (currentRequestType === 'user') {
            requestElement.innerHTML = `
                    <h3>${request.userName} - ${request.bootcampName}</h3>
                    <div class="request-details">
                        <p>트랙: ${request.track}</p>
                        <p>기수: ${request.generation}</p>
                        <p>요청일: ${request.requestDate}</p>
                        <p>상태: <span class="${request.status === 'rejected' ? 'warning-text' : request.status === 'approved' ? 'emphasis-text' : ''}">${getStatusText(request.status)}</span></p>
                    </div>
                    <div class="request-actions">
                        <button class="btn btn-details" data-id="${request.id}">상세 정보</button>
                        ${request.status === 'pending' ? `
                            <button class="btn btn-approve" data-id="${request.id}">승인</button>
                            <button class="btn btn-reject" data-id="${request.id}">거절</button>
                        ` : ''}
                    </div>
                `;
        } else {
            requestElement.innerHTML = `
                    <h3>${request.bootcampName}</h3>
                    <div class="request-details">
                        <p>관계자: ${request.managerName}</p>
                        <p>요청일: ${request.requestDate}</p>
                        <p>상태: <span class="${request.status === 'rejected' ? 'warning-text' : request.status === 'approved' ? 'emphasis-text' : ''}">${getStatusText(request.status)}</span></p>
                    </div>
                    <div class="request-actions">
                        <button class="btn btn-details" data-id="${request.id}">상세 정보</button>
                        ${request.status === 'pending' ? `
                            <button class="btn btn-approve" data-id="${request.id}">승인</button>
                            <button class="btn btn-reject" data-id="${request.id}">거절</button>
                        ` : ''}
                    </div>
                `;
        }
        requestsList.appendChild(requestElement);
    });

    addEventListeners();
}

function getStatusText(status) {
    switch(status) {
        case 'pending': return '대기중';
        case 'approved': return '승인됨';
        case 'rejected': return '거절됨';
        default: return '알 수 없음';
    }
}

function addEventListeners() {
    document.querySelectorAll('.btn-details').forEach(button => {
        button.addEventListener('click', showDetails);
    });

    document.querySelectorAll('.btn-approve').forEach(button => {
        button.addEventListener('click', approveRequest);
    });

    document.querySelectorAll('.btn-reject').forEach(button => {
        button.addEventListener('click', rejectRequest);
    });
}

function showDetails(e) {
    const requestId = e.target.getAttribute('data-id');
    const requests = currentRequestType === 'user' ? userApprovalRequests : bootcampApprovalRequests;
    const request = requests.find(req => req.id === parseInt(requestId));

    const modalContent = document.getElementById('modalContent');
    if (currentRequestType === 'user') {
        modalContent.innerHTML = `
                <p><strong>이름:</strong> ${request.userName}</p>
                <p><strong>부트캠프:</strong> ${request.bootcampName}</p>
                <p><strong>트랙:</strong> ${request.track}</p>
                <p><strong>기수:</strong> ${request.generation}</p>
                <p><strong>시작일:</strong> ${request.startDate}</p>
                <p><strong>종료일:</strong> ${request.endDate}</p>
                <p><strong>요청일:</strong> ${request.requestDate}</p>
                <p><strong>상태:</strong> <span class="${request.status === 'rejected' ? 'warning-text' : request.status === 'approved' ? 'emphasis-text' : ''}">${getStatusText(request.status)}</span></p>
                <p><strong>인증서:</strong></p>
                <img src="${request.certificate}" alt="인증서" class="certificate-preview">
            `;
    } else {
        modalContent.innerHTML = `
                <p><strong>관계자 이름:</strong> ${request.managerName}</p>
                <p><strong>부트캠프명:</strong> ${request.bootcampName}</p>
                <p><strong>요청일:</strong> ${request.requestDate}</p>
                <p><strong>상태:</strong> <span class="${request.status === 'rejected' ? 'warning-text' : request.status === 'approved' ? 'emphasis-text' : ''}">${getStatusText(request.status)}</span></p>
                <p><strong>사업자 등록증:</strong></p>
                <img src="${request.businessCertificate}" alt="사업자 등록증" class="business-certificate-preview">
            `;
    }

    const modal = document.getElementById('detailModal');
    modal.style.display = "flex";
}

function approveRequest(e) {
    const requestId = parseInt(e.target.getAttribute('data-id'));
    const requests = currentRequestType === 'user' ? userApprovalRequests : bootcampApprovalRequests;
    const request = requests.find(req => req.id === requestId);
    if (request) {
        request.status = 'approved';
        console.log(`승인된 요청 ID: ${requestId}`);
        const activeTab = document.querySelector('.category-tab.active');
        displayRequests(activeTab.getAttribute('data-category'), document.getElementById('filterDropdown').value);
    }
}

function rejectRequest(e) {
    const requestId = parseInt(e.target.getAttribute('data-id'));
    const requests = currentRequestType === 'user' ? userApprovalRequests : bootcampApprovalRequests;
    const request = requests.find(req => req.id === requestId);
    if (request) {
        request.status = 'rejected';
        console.log(`거절된 요청 ID: ${requestId}`);
        const activeTab = document.querySelector('.category-tab.active');
        displayRequests(activeTab.getAttribute('data-category'), document.getElementById('filterDropdown').value);
    }
}

// 모달 관련 함수들
function openModal(modalId) {
    const modal = document.getElementById(modalId);
    modal.style.display = "flex";
}

function closeModal(modalId) {
    const modal = document.getElementById(modalId);
    modal.style.display = "none";
}

// 부트캠프 목록 표시
function displayBootcamps() {
    const bootcampList = document.getElementById('bootcampList');
    bootcampList.innerHTML = '';

    bootcamps.forEach(bootcamp => {
        const bootcampElement = document.createElement('div');
        bootcampElement.className = 'bootcamp-item';
        bootcampElement.innerHTML = `
                <span>${bootcamp.managerName} - ${bootcamp.bootcampName}</span>
            `;
        bootcampList.appendChild(bootcampElement);
    });
}

// 부트캠프 등록
function registerBootcamp() {
    const managerName = document.getElementById('managerName').value;
    const bootcampName = document.getElementById('bootcampName').value;

    if (managerName && bootcampName) {
        bootcamps.push({ managerName, bootcampName });
        closeModal('registerModal');
        displayBootcamps();
        openModal('bootcampListModal');
    } else {
        alert('모든 필드를 입력해주세요.');
    }
}

// 이벤트 리스너 설정
document.getElementById('btnBootcampRegister').addEventListener('click', () => {
    displayBootcamps();
    openModal('bootcampListModal');
});

document.getElementById('btnRegisterBootcamp').addEventListener('click', () => {
    closeModal('bootcampListModal');
    openModal('registerModal');
});

document.getElementById('btnRegister').addEventListener('click', registerBootcamp);

// 모달 닫기 버튼 이벤트 리스너
document.querySelectorAll('.close').forEach(closeBtn => {
    closeBtn.addEventListener('click', function() {
        this.closest('.modal').style.display = "none";
    });
});

// 필터 변경 이벤트 리스너
document.getElementById('filterDropdown').addEventListener('change', function(e) {
    const activeTab = document.querySelector('.category-tab.active');
    displayRequests(activeTab.getAttribute('data-category'), e.target.value);
});

// 카테고리 탭 이벤트 리스너
document.querySelectorAll('.category-tab').forEach(tab => {
    tab.addEventListener('click', function() {
        document.querySelectorAll('.category-tab').forEach(t => t.classList.remove('active'));
        this.classList.add('active');
        displayRequests(this.getAttribute('data-category'), document.getElementById('filterDropdown').value);
    });
});

// 승인 요청 유형 탭 이벤트 리스너
document.querySelectorAll('.approval-type-tab').forEach(tab => {
    tab.addEventListener('click', function() {
        document.querySelectorAll('.approval-type-tab').forEach(t => t.classList.remove('active'));
        this.classList.add('active');
        currentRequestType = this.getAttribute('data-type');
        document.getElementById('requestTypeTitle').textContent =
            currentRequestType === 'user' ? '승인 요청(유저)' : '승인 요청(부트캠프)';
        displayRequests('all', 'newest');
    });
});

// 초기 요청 목록 표시 (전체 요청 표시)
displayRequests('all', 'newest');

// 모달 배경 클릭 이벤트 추가
document.querySelectorAll('.modal').forEach(modal => {
    modal.addEventListener('click', (event) => {
        if (event.target === modal) {
            closeModal(modal.id);
        }
    });
});