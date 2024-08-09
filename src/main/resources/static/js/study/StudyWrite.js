document.getElementById('studyForm').addEventListener('submit', function (e) {
  e.preventDefault();

  // 폼 데이터 수집
  const selectedCategory = document.querySelector('input[name="category"]:checked')?.value;
  const studyTitle = document.getElementById('title')?.value;
  const studyContent = document.getElementById('description')?.value;
  const maxCount = document.getElementById('maxMembers')?.value;
  const periodExpected = document.getElementById('duration')?.value;
  const cycle = document.getElementById('meetingFrequency')?.value;

  // 데이터 유효성 검증
  if (!selectedCategory || !studyTitle || !studyContent || !maxCount || !periodExpected || !cycle) {
    Swal.fire({
      title: '입력 오류',
      text: '모든 필드를 올바르게 입력해 주세요.',
      icon: 'error',
      confirmButtonText: '확인'
    });
    return;
  }

  // 서버로 전송할 데이터 준비
  const studyData = {
    category: selectedCategory,
    title: studyTitle,
    content: studyContent,
    maxCount: parseInt(maxCount, 10),  // 문자열을 숫자로 변환
    periodExpected: periodExpected,
    cycle: cycle
  };

  // URL 가져오기
  const submitUrl = document.querySelector('.submit-button').getAttribute('data-submit-url');

  // 토큰 가져오기
  const accessToken = localStorage.getItem('accessToken');
  if (!accessToken) {
    Swal.fire({
      title: '인증 오류',
      text: '로그인 세션이 만료되었습니다. 다시 로그인해 주세요.',
      icon: 'error',
      confirmButtonText: '확인'
    }).then(() => {
      window.location.href = '/auth';
    });
    return;
  }

  fetch(submitUrl, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${accessToken}`
    },
    body: JSON.stringify(studyData)
  })
  .then(data => {
    if (data.statuscode === "201") {
      Swal.fire({
        title: '스터디 작성 성공',
        text: '스터디가 성공적으로 제출되었습니다!',
        icon: 'success',
        confirmButtonText: '확인'
      }).then(() => {
        if (data.data && data.data.id) {
          const studyId = data.data.id;
          window.location.href = `/study/detail/${studyId}`;
        } else {
          console.error('스터디 ID가 반환되지 않았습니다.');
        }
      });
    } else {
      Swal.fire({
        title: '스터디 작성 실패',
        text: data.message || '스터디 작성 중 오류가 발생했습니다.',
        icon: 'error',
        confirmButtonText: '확인'
      });
    }
  })
  .catch(error => {
    Swal.fire({
      title: '네트워크 오류',
      text: error.message || '네트워크 오류가 발생했습니다.',
      icon: 'error',
      confirmButtonText: '확인'
    });
  });
});

function cancelStudy() {
  if (confirm('작성 중인 모집글을 취소하시겠습니까?')) {
    // 스터디 작성 취소 후 리디렉션
    const cancelUrl = document.querySelector('.cancel-button').getAttribute('data-cancel-url');
    window.location.href = cancelUrl;
  }
}