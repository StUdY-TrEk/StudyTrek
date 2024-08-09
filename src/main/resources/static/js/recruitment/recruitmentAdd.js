const courseForm = document.getElementById('courseForm');
const classTypeSelect = document.getElementById('classTypeSelect');
const costTypeSelect = document.getElementById('costTypeSelect');
const fieldTypeSelect = document.getElementById('fieldTypeSelect');
const courseDifficultySelect = document.getElementById('courseDifficulty');
const selectedCategories = document.getElementById('selectedCategories');
const institutionInput = document.getElementById('institution');
const institutionResults = document.getElementById('institutionResults');
const submitButton = document.getElementById('submitButton');
const cancelButton = document.getElementById('cancelButton');

let selectedCategoryList = {
    classType: '',
    costType: '',
    fieldType: '',
    difficulty: ''
};

// 운영기관 목록 (실제로는 서버에서 가져와야 함)
const institutions = [
    "코딩 아카데미",
    "테크 학원",
    "IT 교육센터",
    "프로그래밍 스쿨",
    "개발자 양성소",
    "디지털 캠퍼스",
    "소프트웨어 교육원",
    "웹 개발 아카데미",
    "데이터 사이언스 스쿨",
    "AI 교육센터"
];

// Flatpickr 초기화
flatpickr("#coursePeriod", {
    mode: "range",
    dateFormat: "Y-m-d",
    disable: [], // 주말 비활성화 설정 제거
    locale: {
        firstDayOfWeek: 6 // 토요일부터 시작
    }
});

flatpickr("#courseRecruitment", {
    mode: "range",
    dateFormat: "Y-m-d",
    disable: [], // 주말 비활성화 설정 제거
    locale: {
        firstDayOfWeek: 6 // 토요일부터 시작
    }
});

function updateSelectedCategories() {
    selectedCategories.innerHTML = '';
    for (const [key, value] of Object.entries(selectedCategoryList)) {
        if (value) {
            const span = document.createElement('span');
            span.className = 'selected-category';
            span.innerHTML = `${value} <span class="remove-category" data-category="${key}">✕</span>`;
            selectedCategories.appendChild(span);
        }
    }

    // 삭제 버튼에 이벤트 리스너 추가
    document.querySelectorAll('.remove-category').forEach(button => {
        button.addEventListener('click', function() {
            const categoryToRemove = this.getAttribute('data-category');
            selectedCategoryList[categoryToRemove] = '';
            updateSelectedCategories();
            // 해당 select 요소의 선택을 초기화
            if (categoryToRemove === 'difficulty') {
                courseDifficultySelect.value = '';
            } else {
                document.getElementById(`${categoryToRemove}Select`).value = '';
            }
            validateForm();
        });
    });
}

[classTypeSelect, costTypeSelect, fieldTypeSelect, courseDifficultySelect].forEach(select => {
    select.addEventListener('change', function() {
        const categoryType = this.id === 'courseDifficulty' ? 'difficulty' : this.id.replace('Select', '');
        selectedCategoryList[categoryType] = this.value;
        updateSelectedCategories();
        validateForm();
    });
});

institutionInput.addEventListener('input', function() {
    const searchTerm = this.value.toLowerCase();
    const filteredInstitutions = institutions.filter(inst =>
        inst.toLowerCase().includes(searchTerm)
    );

    institutionResults.innerHTML = '';
    filteredInstitutions.forEach(inst => {
        const div = document.createElement('div');
        div.className = 'institution-item';
        div.textContent = inst;
        div.addEventListener('click', function() {
            institutionInput.value = inst;
            institutionResults.innerHTML = '';
            validateForm();
        });
        institutionResults.appendChild(div);
    });
});

// 다른 곳을 클릭하면 결과 목록 숨기기
document.addEventListener('click', function(e) {
    if (institutionInput && institutionResults &&
        !institutionInput.contains(e.target) &&
        !institutionResults.contains(e.target)) {
        institutionResults.innerHTML = '';
    }
});

function validateForm() {
    let isValid = true;
    const fields = [
        { id: 'courseTitle', errorId: 'courseTitleError', message: '과정 제목을 입력해주세요.' },
        { id: 'institution', errorId: 'institutionError', message: '운영기관을 선택해주세요.' },
        { id: 'courseIntro', errorId: 'courseIntroError', message: '과정 소개를 입력해주세요.' },
        { id: 'courseContent', errorId: 'courseContentError', message: '교육 내용을 입력해주세요.' },
        { id: 'coursePeriod', errorId: 'coursePeriodError', message: '참여 기간을 선택해주세요.' },
        { id: 'courseRecruitment', errorId: 'courseRecruitmentError', message: '모집 기간을 선택해주세요.' },
        { id: 'courseTime', errorId: 'courseTimeError', message: '수업 시간을 입력해주세요.' }
    ];

    fields.forEach(field => {
        const element = document.getElementById(field.id);
        const errorElement = document.getElementById(field.errorId);
        if (!element.value.trim()) {
            errorElement.textContent = field.message;
            isValid = false;
        } else {
            errorElement.textContent = '';
        }
    });

    // 카테고리 유효성 검사
    const categoryError = document.getElementById('categoryError');
    if (!selectedCategoryList.classType || !selectedCategoryList.costType || !selectedCategoryList.fieldType || !selectedCategoryList.difficulty) {
        categoryError.textContent = '모든 카테고리를 선택해주세요.';
        isValid = false;
    } else {
        categoryError.textContent = '';
    }

    submitButton.disabled = !isValid;
}

// 모든 입력 필드에 대해 이벤트 리스너 추가
courseForm.querySelectorAll('input, textarea, select').forEach(element => {
    element.addEventListener('input', validateForm);
});

// 폼 제출 이벤트
courseForm.addEventListener('submit', function(e) {
    e.preventDefault(); // 기본 제출 동작 방지

    if (confirm('과정을 등록하시겠습니까?')) {
        const selectedClassType = document.getElementById('classTypeSelect').value;
        const selectedCostType = document.getElementById('costTypeSelect').value;
        const selectedFieldType = document.getElementById('fieldTypeSelect').value;
        const selectedDifficulty = document.getElementById('courseDifficulty').value;

        const courseTitle = document.getElementById('courseTitle').value;
        const institution = document.getElementById('institution').value;
        const courseIntro = document.getElementById('courseIntro').value;
        const courseContent = document.getElementById('courseContent').value;
        const coursePeriod = document.getElementById('coursePeriod').value; // 날짜 범위 선택기에서 문자열로 반환됨
        const courseRecruitment = document.getElementById('courseRecruitment').value;
        const courseTime = document.getElementById('courseTime').value;

        // 날짜 범위 나누기
        const [periodDate, periodToDate] = coursePeriod.split(' to ');
        const [recruitDate, recruitToDate] = courseRecruitment.split(' to ');

        // 서버로 전송할 데이터 준비
        const formData = {
            place: selectedClassType,
            cost: selectedCostType,
            trek: selectedFieldType,
            level: selectedDifficulty,
            title: courseTitle,
            campName: institution,
            process: courseIntro,
            content: courseContent,
            campStart: periodDate,
            campEnd: periodToDate,
            classTime: courseTime,
            recruitStart: recruitDate,
            recruitEnd: recruitToDate
        };

        // 서버로 데이터 전송
        fetch('/api/camps', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${localStorage.getItem('accessToken')}`
            },
            body: JSON.stringify(formData) // JSON 문자열로 변환하여 전송
        })
        .then(response => response.json())
        .then(data => {
            if (data.statuscode === '201') {
                Swal.fire({
                    title: '등록 완료',
                    text: '모집글이 성공적으로 등록되었습니다.',
                    icon: 'success',
                    confirmButtonText: '확인'
                }).then(() => {
                    if (data.data && data.data.id) {
                        const campId = data.data.id;
                        const detailUrl = `/camp/${campId}`;
                        window.location.href = detailUrl;
                    } else {
                        console.error('캠프 ID가 반환되지 않았습니다.');
                    }
                });
            } else {
                Swal.fire({
                    title: '등록 실패',
                    text: `오류 발생: ${data.message}`,
                    icon: 'error',
                    confirmButtonText: '확인'
                });
            }
        })
        .catch(error => {
            console.error('서버 오류:', error);
            Swal.fire({
                title: '등록 실패',
                text: '서버와의 통신 오류가 발생했습니다.',
                icon: 'error',
                confirmButtonText: '확인'
            });
        });
    }
});


// 취소 버튼 이벤트
cancelButton.addEventListener('click', function(e) {
    if (confirm('작성을 취소하시겠습니까? 입력한 내용은 저장되지 않습니다.')) {
        // 폼 초기화
        courseForm.reset();

        // 선택된 카테고리 초기화
        selectedCategoryList = {
            classType: '',
            costType: '',
            fieldType: '',
            difficulty: ''
        };
        updateSelectedCategories();

        // 목록 페이지로 이동
        window.location.href = '/camp';  // 실제 목록 페이지 URL로 수정 필요
    }
});

// 초기 카테고리 설정 및 폼 유효성 검사
updateSelectedCategories();
validateForm();