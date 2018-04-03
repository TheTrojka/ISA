import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchEstablishmentsComponent } from './search-establishments.component';

describe('SearchEstablishmentsComponent', () => {
  let component: SearchEstablishmentsComponent;
  let fixture: ComponentFixture<SearchEstablishmentsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchEstablishmentsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchEstablishmentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
