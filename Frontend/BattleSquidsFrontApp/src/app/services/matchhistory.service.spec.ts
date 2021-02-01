import { TestBed } from '@angular/core/testing';

import { MatchhistoryService } from './matchhistory.service';

describe('MatchhistoryService', () => {
  let service: MatchhistoryService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MatchhistoryService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
