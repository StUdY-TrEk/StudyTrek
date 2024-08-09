// 검색 폼 제출 시 호출되는 함수
function performSearch(event) {
  event.preventDefault();
  const searchType = document.getElementById('searchType').value;
  const searchQuery = document.getElementById('searchQuery').value;

  console.log(`검색 유형: ${searchType}, 검색어: ${searchQuery}`);
  alert(`검색 유형: ${searchType}\n검색어: ${searchQuery}`);
}

// 필터 버튼 클릭 시 필터 메뉴 토글
function toggleFilter(element) {
  if (element) {
    element.classList.toggle('active');
    updateSelectedFilters();
  } else {
    const filterContainer = document.getElementById('filterContainer');
    filterContainer.style.display = filterContainer.style.display === 'none'
        ? 'block' : 'none';
  }
}

// 필터 초기화
function resetFilters() {
  const activeFilters = document.querySelectorAll('.filter-option.active');
  activeFilters.forEach(filter => filter.classList.remove('active'));
  updateSelectedFilters();
}

// 필터 요청을 서버에 전송하여 필터링된 결과를 가져옵니다.
function applyFilters() {
  const filterRequest = getFilterRequest(); // 현재 필터 요청 객체를 가져옵니다.

  // 필터 요청 객체를 쿼리 문자열로 변환합니다.
  const queryString = Object.keys(filterRequest)
  .filter(key => filterRequest[key].length > 0) // 빈 배열 필터링
  .map(key => filterRequest[key].map(
      value => `${encodeURIComponent(key)}=${encodeURIComponent(value)}`).join(
      '&'))
  .join('&');

  fetch(`/api/camps/filter?${queryString}`, {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json'
    }
  })
  .then(response => response.json())
  .then(data => {
    console.log('Filtered Results:', data);
    localStorage.setItem('filteredResults', JSON.stringify(data)); // 필터링된 결과를 로컬 스토리지에 저장
    updateResults(data);
  })
  .catch(error => console.error('Error:', error));

  toggleFilter();
}

// 활성화된 필터 옵션을 기반으로 필터 요청 객체를 생성합니다.
function getFilterRequest() {
  const activeFilters = document.querySelectorAll('.filter-option.active');

  const filterRequest = {
    trek: Array.from(activeFilters)
    .filter(el => ['프론트엔드', '백엔드', '풀스택', 'Android', 'iOS', '게임', 'AI', '데이터',
      'UI/UX'].includes(el.textContent.trim()))
    .map(el => el.textContent.trim()), // 트랙 필터 요청을 배열로 설정
    place: Array.from(activeFilters)
    .filter(el => ['온라인', '오프라인', '혼합'].includes(el.textContent.trim()))
    .map(el => el.textContent.trim()), // 환경 필터 요청을 배열로 설정
    cost: Array.from(activeFilters)
    .filter(el => ['국비', '사비', '무료'].includes(el.textContent.trim()))
    .map(el => el.textContent.trim()) // 비용 필터 요청을 배열로 설정
  };

  return filterRequest;
}

// 선택된 필터 옵션을 화면 상단의 선택된 필터 영역에 추가합니다.
function updateSelectedFilters() {
  const selectedFiltersContainer = document.getElementById('selectedFilters');
  selectedFiltersContainer.innerHTML = '';

  const activeFilters = document.querySelectorAll('.filter-option.active');
  activeFilters.forEach(filter => {
    const filterElement = document.createElement('span');
    filterElement.className = 'selected-filter';
    filterElement.innerHTML = `
      ${filter.textContent}
      <span class="remove" onclick="removeFilter(this, '${filter.textContent}')">&times;</span>
    `; // 선택된 필터를 화면에 표시하고, 제거 버튼을 추가합니다.
    selectedFiltersContainer.appendChild(filterElement); // 선택된 필터를 선택된 필터 컨테이너에 추가합니다.
  });
}

// 선택된 필터를 비활성화하고 화면에서 제거하는 함수
function removeFilter(element, filterText) {
  const filterOption = Array.from(
      document.querySelectorAll('.filter-option')).find(
      option => option.textContent === filterText);
  if (filterOption) {
    filterOption.classList.remove('active'); // 필터 옵션에서 'active' 클래스를 제거합니다.
  }
  element.parentElement.remove();
}

function toggleSection(header) {
  const section = header.closest('.filter-section');
  section.classList.toggle('collapsed');
}

// 필터 적용 버튼 클릭 시 필터를 적용하는 함수를 호출합니다.
document.querySelector('.main-filter-apply').addEventListener('click',
    applyFilters);