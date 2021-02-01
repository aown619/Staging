import { TestBed } from '@angular/core/testing';

import { InviteTypeService } from './inviteType.service';

describe('InviteTypeService', () => {
  let service: InviteTypeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(InviteTypeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
