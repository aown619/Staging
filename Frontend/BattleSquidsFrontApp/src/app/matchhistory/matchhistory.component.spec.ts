import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MatchhistoryComponent } from './matchhistory.component';

describe('MatchhistoryComponent', () => {
  let component: MatchhistoryComponent;
  let fixture: ComponentFixture<MatchhistoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MatchhistoryComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MatchhistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
