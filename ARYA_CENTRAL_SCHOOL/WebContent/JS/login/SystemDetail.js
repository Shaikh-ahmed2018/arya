var ip_addresses = {};
var errors = [];

function extract_ipv4(str) {
  var regexp = /\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3}/g;
  var t;
  while (t = regexp.exec(str)) {
    ip_addresses[t[0]] = 1;
  }
  if (publicIp) {
    delete ip_addresses[publicIp];
  }
  delete ip_addresses["0.0.0.0"];
  delete ip_addresses["127.0.0.1"];
  internal_ip.value = Object.keys(ip_addresses).join(", ") ; 
}
window.extract_ipv4 = extract_ipv4;

function display_error(msg) {
  errors.push(msg);
  error.textContent = msg.join(", ");
}

var PeerConnection = window.RTCPeerConnection || window.mozRTCPeerConnection || window.webkitRTCPeerConnection;

window.localPeerConnection = new PeerConnection(null, {optional: [{RtpDataChannels: true}]});
try {
  sendChannel = localPeerConnection.createDataChannel("", {reliable: false});
} catch (e) {
  display_error(e.message);
}
localPeerConnection.onicecandidate = 
  function(e) {
    if (e.candidate) {
      extract_ipv4(e.candidate.candidate);
    }
  }
localPeerConnection.createOffer(function(desc){
  extract_ipv4(desc.sdp);
  localPeerConnection.setLocalDescription(desc);
}, display_error);